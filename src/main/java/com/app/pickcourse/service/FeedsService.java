package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.PaginationOnePage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StandardSessionIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FeedsService {
    private final ReplyDAO replyDAO;
    private final GeneralReplyDAO generalReplyDAO;
    private final TogetherReplyDAO togetherReplyDAO;
    private final RealReplyDAO realReplyDAO;
    private final ReportDAO reportDAO;
    private final ReplyReportDAO replyReportDAO;
    private final FeedReportDAO feedReportDAO;
    private final FeedDAO feedDAO;
    private final GeneralFeedDAO generalFeedDAO;
    private final TogetherFeedDAO togetherFeedDAO;
    private final RealFeedDAO realFeedDAO;
    private final TagDAO tagDAO;
    private final FileDAO fileDAO;
    private final GeneralFileDAO generalFileDAO;
    private final TogetherFileDAO togetherFileDAO;
    private final RealFileDAO realFileDAO;
    private final PlanDAO2 planDAO;
    private final MemberDAO2 memberDAO;

    public List<ReplyListDTO> getReplyList(Long memberId, Long feedId, PaginationOnePage pagination) {
        pagination.create(replyDAO.getCountAll(feedId));
        log.info("replyDAO.getCountAll(feedId) "+replyDAO.getCountAll(feedId));

        List<ReplyListDTO> list = replyDAO.getReplyList(feedId, pagination);

        // 로그인 회원과 작성자가 같으면 '삭제' 다르면 '신고'
        list.forEach( reply -> {
            if ( reply.getMemberId() == memberId){
                reply.setReplyAction("삭제");
            } else {
                reply.setReplyAction("신고");
            }

        });
        return list;
    }

    public void deleteReplyList(Long id) {
        // 신고 내역 삭제 (한건의 댓글에 많은 신고), tbl_report는 자동삭제 되지 않기 때문에 삭제 대상 id를 가져와야 함.
        // on delete cascade로 자동 삭제되므로 가장 우선적으로 실행
        List<Long> reportIds = replyReportDAO.selectId(id);

        replyDAO.deleteReplyList(id);
        generalReplyDAO.deleteReplyList(id);
        togetherReplyDAO.deleteReplyList(id);
        realReplyDAO.deleteReplyList(id);
        reportIds.forEach( reportId -> {
            log.info("reportId  "+reportId);
            replyReportDAO.deleteReplyList(reportId);   // on delete cascade로 자동 삭제되지만 한번 더 확인
            reportDAO.deleteReplyList(reportId);
        });

    }

    public void postReportReplyList(ReportVO reportVO, Long memberId) {
        ReportIdVO reportId = new ReportIdVO();
        reportDAO.postReportReplyList(reportId); // 슈퍼키 가져오기
        replyReportDAO.postReportReplyList(reportId.getId(), reportVO.getId(), reportVO.getReportedReason(), memberId);

    }

    public void postReplyList(ReplyVO replyVO, Long memberId) {
        // 댓글 슈퍼키 입력
        replyDAO.postReplyList(replyVO);

        // 피드 종류 알아보기
        String typeOfFeed = replyDAO.selectTypeOfFeed(replyVO.getFeedId());

        // 피드 종류별 댓글 입력
        replyVO.setMemberId(memberId);
        if (typeOfFeed.equals("GENERAL FEED")) {
            generalReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("REAL FEED")) {
            realReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("TOGETHER FEED")) {
            togetherReplyDAO.postReplyList(replyVO);
        }

    }

    public List<ReplyListDTO> getMyReplyList(Long memberId, PaginationOnePage pagination) {
        pagination.create(replyDAO.getMyCountAll(memberId));

        List<ReplyListDTO> list = replyDAO.getMyReplyList(memberId, pagination);

        // 삭제 버튼 생성
        list.forEach( reply -> {
                reply.setReplyAction("삭제");
        });

        return list;
    }

    public void postFeedWrite(Long memberId, FeedDTO feedDTO) {
        // 피드 슈퍼키 입력
        feedDAO.postFeedWrite(feedDTO);
        // 피드 타입에 따라 제네럴, 투게더 피드 입력
        if (feedDTO.getFeedType().equals("GENERAL")) {
            generalFeedDAO.postFeedWrite(memberId, feedDTO.toFeedVO());
        } else if (feedDTO.getFeedType().equals("TOGETHER")) {
            togetherFeedDAO.postFeedWrite(memberId, feedDTO.toFeedVO());
        }

        // 태그 입력
        if (feedDTO.getTags() != null && feedDTO.getTags().size() != 0) {
            feedDTO.getTags().forEach( tagContent -> {
                tagDAO.postFeedWrite(tagContent, feedDTO.getId());
            });
        }

        // 파일 입력
        if (feedDTO.getFiles() != null && feedDTO.getFiles().size() != 0) {
            feedDTO.getFiles().forEach( file -> {

                fileDAO.postFeedWrite(file); // 슈퍼키 입력

                // 피드 타입에 따라 제네럴, 투게더 파일 입력
                if (feedDTO.getFeedType().equals("GENERAL")) {
                    generalFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                } else if (feedDTO.getFeedType().equals("TOGETHER")) {
                    togetherFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                }
            });
        }
    }

    public void postRealWrite(Long memberId, RealDTO realDTO) {
        // 피드 슈퍼키 입력
        feedDAO.postRealWrite(realDTO);
        // 리얼 입력
        realFeedDAO.postFeedWrite(realDTO.getId(), memberId, realDTO.getPlanId());

        // 태그 입력
        if (realDTO.getTags() != null && realDTO.getTags().size() != 0) {
            realDTO.getTags().forEach( tagContent -> {
                tagDAO.postFeedWrite(tagContent, realDTO.getId());
            });
        }

        // 파일 입력
        if (realDTO.getFiles() != null && realDTO.getFiles().size() != 0) {
            realDTO.getFiles().forEach( file -> {

                fileDAO.postFeedWrite(file); // 슈퍼키 입력

                realFileDAO.postFeedWrite(file.getId(), realDTO.getId());
            });
        }
    }

    public FeedDTO getFeedModify(Long id, String feedType) {
        FeedDTO feedDTO = null;
        // 피드 정보 가져오기
        if (feedType.equals("GENERAL")) {
           feedDTO = generalFeedDAO.getFeedModify(id);
        } else if (feedType.equals("TOGETHER")) {
            feedDTO = togetherFeedDAO.getFeedModify(id);
        }

        // tag 가져오기
        List<String> tagContent = tagDAO.getFeedModify(id);
        feedDTO.setTags(tagContent);

        // file 가져오기
        List<FileVO> fileList = null;
        if (feedType.equals("GENERAL")) {
            fileList = generalFileDAO.getFeedModify(id);
        } else if (feedType.equals("TOGETHER")) {
            fileList = togetherFileDAO.getFeedModify(id);
        }
        feedDTO.setFiles(fileList);

        return feedDTO;
    }

    public void postFeedModify(FeedDTO feedDTO) {

        // 피드 정보 수정
        feedDAO.postFeedModify(feedDTO.toFeedVO());

        // tag 삭제 후 재입력
        if (feedDTO.getTags() != null && feedDTO.getTags().size() != 0) {
            // 삭제
            tagDAO.deleteFeedModify(feedDTO.getId());
            // 재등록
            feedDTO.getTags().forEach( tagContent -> {
                tagDAO.postFeedWrite(tagContent, feedDTO.getId());
            });
        }

        // 삭제대상 기존 파일file 삭제

        // 삭제
        if (feedDTO.getDeleteFileId() != null && feedDTO.getDeleteFileId().size() != 0) {
            feedDTO.getDeleteFileId().forEach( id -> {
                fileDAO.deleteFeedModify(id);
                if (feedDTO.getFeedType().equals("GENERAL")) {
                    generalFileDAO.deleteFeedModify(id);
                } else if (feedDTO.equals("TOGETHER")) {
                    togetherFileDAO.deleteFeedModify(id);
                }
            });
        }

        // 신규 파일 등록
        if (feedDTO.getFiles() != null && feedDTO.getFiles().size() != 0) {
            feedDTO.getFiles().forEach( file -> {
                fileDAO.postFeedWrite(file); // 슈퍼키 입력
                if (feedDTO.getFeedType().equals("GENERAL")) {
                    generalFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                } else if (feedDTO.getFeedType().equals("TOGETHER")) {
                    togetherFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                }
            });
        }
    }

    public void deleteFeedModify(Long id, String feedType) {

        // 피드 정보 삭제
        feedDAO.deleteFeedModify(id);

        // 피드 타입에 따라 제네럴, 투게더 피드 삭제
        if (feedType.equals("GENERAL")) {
            generalFeedDAO.deleteFeedModify(id);
        } else if (feedType.equals("TOGETHER")) {
            togetherFeedDAO.deleteFeedModify(id);
        }

        // tag 삭제
        tagDAO.deleteFeedModify(id);

        // 파일 삭제
        fileDAO.deleteFeedModifyByFeedId(id);
        if (feedType.equals("GENERAL")) {
            generalFileDAO.deleteFeedModifyByFeedId(id);
        } else if (feedType.equals("TOGETHER")) {
            togetherFileDAO.deleteFeedModifyByFeedId(id);
        }

    }

    public RealDTO getRealModify(Long id) {

        // 리얼 정보 가져오기
        RealDTO realDTO  = realFeedDAO.getRealModify(id);

        // tag 가져오기
        List<String> tagContent = tagDAO.getFeedModify(id);
        realDTO.setTags(tagContent);

        // file 가져오기
        List<FileVO> fileList = realFileDAO.getRealModify(id);
        realDTO.setFiles(fileList);

        return realDTO;
    }

    public void postRealModify(RealDTO realDTO) {

        // 리얼 정보 수정
        feedDAO.postFeedModify(realDTO.toFeedVO());

        // tag 삭제 후 재입력
        if (realDTO.getTags() != null && realDTO.getTags().size() != 0) {
            // 삭제
            tagDAO.deleteFeedModify(realDTO.getId());
            // 재등록
            realDTO.getTags().forEach( tagContent -> {
                tagDAO.postFeedWrite(tagContent, realDTO.getId());
            });
        }

        // 삭제대상 기존 파일file 삭제
        // 삭제
        if (realDTO.getDeleteFileId() != null && realDTO.getDeleteFileId().size() != 0) {
            realDTO.getDeleteFileId().forEach( id -> {
                fileDAO.deleteFeedModify(id);
                realFileDAO.deleteRealModify(id);
            });
        }

        // 신규 파일 등록
        if (realDTO.getFiles() != null && realDTO.getFiles().size() != 0) {
            realDTO.getFiles().forEach( file -> {
                fileDAO.postFeedWrite(file); // 슈퍼키 입력
                realFileDAO.postFeedWrite(file.getId(), realDTO.getId());
            });
        }
    }

    public void deleteRealModify(Long id) {

        // 피드 정보 삭제
        feedDAO.deleteFeedModify(id);

        // 리얼 피드 삭제
        realFeedDAO.deleteRealModify(id);

        // tag 삭제
        tagDAO.deleteFeedModify(id);

        // 파일 삭제
        fileDAO.deleteFeedModify(id);
        realFileDAO.deleteRealModifyByFeedId(id);

    }

    public List<FeedListDTO> getFeedList(String listType) {
        List<FeedListDTO> list = null;

        // 피드 가져오기
        if (listType.equals("ALL")) {
            list = feedDAO.getFeedList();
        } else if (listType.equals("TOGETHER")) {
            list = togetherFeedDAO.getFeedList();
        } else if (listType.equals("REAL")) {
            list = realFeedDAO.getFeedList();
        }

        // tag, 파일 가져오기
        list.forEach(feed -> {
            feed.setTags(tagDAO.getFeedModify(feed.getId()));

            List<FileVO> fileList = null;
            if (feed.getFeedType().equals("GENERAL")) {
                fileList = generalFileDAO.getFeedModify(feed.getId());
            } else if (feed.getFeedType().equals("TOGETHER")) {
                fileList = togetherFileDAO.getFeedModify(feed.getId());
            } else if (feed.getFeedType().equals("REAL")) {
                fileList = realFileDAO.getRealModify(feed.getId());
            }
            feed.setFiles(fileList);
        });

        return list;
    }

    public List<FeedListDTO> getMyFeedList(Long memberId, String listType) {
        List<FeedListDTO> list = null;

        // 피드 가져오기
        if (listType.equals("ALL")) {
            list = feedDAO.getMyFeedList(memberId);
        } else if (listType.equals("TOGETHER")) {
            list = togetherFeedDAO.getMyFeedList(memberId);
        } else if (listType.equals("REAL")) {
            list = realFeedDAO.getMyFeedList(memberId);
        }

        // tag, 파일 가져오기
        list.forEach(feed -> {
            feed.setTags(tagDAO.getFeedModify(feed.getId()));

            List<FileVO> fileList = null;
            if (feed.getFeedType().equals("GENERAL")) {
                fileList = generalFileDAO.getFeedModify(feed.getId());
            } else if (feed.getFeedType().equals("TOGETHER")) {
                fileList = togetherFileDAO.getFeedModify(feed.getId());
            } else if (feed.getFeedType().equals("REAL")) {
                fileList = realFileDAO.getRealModify(feed.getId());
            }
            feed.setFiles(fileList);
        });

        return list;
    }

    public void postReportFeedList(ReportVO reportVO, Long memberId) {
        ReportIdVO reportId = new ReportIdVO();
        reportDAO.postReportReplyList(reportId); // 슈퍼키 가져오기
        feedReportDAO.postReportFeedList(reportId.getId(), reportVO.getId(), reportVO.getReportedReason(), memberId);

    }

    public List<TourListDTO> getTourList(Long memberId, PaginationOnePage pagination) {

        pagination.create(planDAO.getCountAllByMemberId(memberId));
        List<TourListDTO> list = planDAO.getTourList(memberId,pagination);

        return list;
    }

    public MemberFileDTO getMemberFileInfo(Long id) {
        return memberDAO.getMemberFileInfo(id);
    }


}

