package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.PaginationParticipants;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminService {

    private final AdminDAO adminDAO;
    private final MemberDAO memberDAO;
    private final CourseDAO courseDAO;
    private final VolunteerDAO volunteerDAO;
    private final PathDAO pathDAO;
    private final VolunteerExcludeDAO volunteerExcludeDAO;
    private final VolunteerIncludeDAO volunteerIncludeDAO;
    private final VolunteerPrepareDAO volunteerPrepareDAO;
    private final VolunteerScheduleDAO volunteerScheduleDAO;
    private final ReportDAO reportDAO;
    private final FeedReportDAO feedReportDAO;
    private final ReplyReportDAO replyReportDAO;
    private final FeedDAO feedDAO;
    private final ReplyDAO replyDAO;
    private final NoticeDAO noticeDAO;
    private final VolunteerParticipantDAO volunteerParticipantDAO;

    // 관리자 목록 조회
    public List<AdminVO> getManageAdminList(Pagination pagination, Search search) {
        pagination.create(adminDAO.getCountAll(search));
        return adminDAO.getManageAdminList(pagination, search);
    }

    // 관리자 등록
    public void postManageAdminList(AdminVO adminVO) throws DuplicateException {
        // 중복 체크
        if (adminDAO.isAdminAccount(adminVO.getAdminAccount()) > 0){
            throw new DuplicateException("adim account  "+adminVO.getAdminAccount()+"  already exist !!!");
        }

        adminDAO.postManageAdminList(adminVO);
    }

    // 관리자 삭제
    public void deleteManageAdminList(String selectedIds) {
        // 삭제할 대상 id를 Long으로 변환
        List<Long> idList = Arrays.asList(selectedIds.split(",")).
                stream().map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(adminId -> {
            adminDAO.deleteManageAdminList(adminId);
        });
    }

    // 회원 목록
    public List<MemberVO> getMemberList(Character isAct, Pagination pagination, Search search) {

        List<MemberVO> list = null;
        if (isAct == null || isAct == ' ') {
            pagination.create(memberDAO.getCountAll(search));
            list = memberDAO.getMemberList(pagination, search);
        } else if (isAct.equals('Y')) {  // 활동 회원
            pagination.create(memberDAO.getCountAllActY(search));
            list = memberDAO.getMemberListActY(pagination, search);
        } else if (isAct.equals('N')) { // 비활동 회원
            pagination.create(memberDAO.getCountAllActN(search));
            list = memberDAO.getMemberListActN(pagination, search);
        }
        return list;
    }

    // 회원 정지
    public void patchMemberListPause(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.patchMemberListPause(memberId);
        });
    }

    // 회원 정지 해제
    public void patchMemberListRestart(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());


        idList.forEach(memberId -> {
            memberDAO.patchMemberListRestart(memberId);
        });
    }

    // 회원 삭제
    public void deleteMemberList(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.deleteMemberList(memberId);
        });

    }

    // 코스 등록
    public void postAddCourse(CourseDTO courseDTO) {

        // 코스정보 입력
        courseDAO.postAddCourse(courseDTO);

        if (courseDTO.getCourseIsVolunteer().equals('Y')){

            // 봉사코스 정보 입력
            volunteerDAO.postAddCourse(courseDTO.toVolunteerVO());

            // 일일 계획 입력
            if (courseDTO.getScheduleContents() != null){
                courseDTO.getScheduleContents().forEach(scheduleContent -> {
                    volunteerScheduleDAO.postAddCourse(scheduleContent, courseDTO.getId());
                });
            }
        }

        // 경로 입력
        courseDTO.getPaths().forEach(path -> {
            path.setCourseId(courseDTO.getId());
            pathDAO.postAddCourse(path);
        });

        // 불포함 사항 입력
        if (courseDTO.getExcludeContents() != null){
            courseDTO.getExcludeContents().forEach(excludeContent -> {
                volunteerExcludeDAO.postAddCourse(excludeContent, courseDTO.getId());
            });
        }

        // 포함 사항 입력
        if (courseDTO.getIncludeContents() != null){
            courseDTO.getIncludeContents().forEach(includeContent -> {
                volunteerIncludeDAO.postAddCourse(includeContent, courseDTO.getId());
            });
        }

        // 준비물 입력
        if (courseDTO.getPrepareContents() != null){
            courseDTO.getPrepareContents().forEach(prepareContent -> {
                volunteerPrepareDAO.postAddCourse(prepareContent, courseDTO.getId());
            });
        }

    }

    // 코스 목록
    public List<CourseListDTO> getCourseList(Pagination pagination, Search search) {
        pagination.create(courseDAO.getCountAll(search));
        return courseDAO.getCourseList(pagination,search);
    }

    // 코스 수정
    public void patchCourseList(String courseId, String courseType) {
        courseDAO.patchCourseListExpire(courseType);
        courseDAO.patchCourseListRegist(Long.parseLong(courseId), courseType);
    }

    // 코스 상세 조회
    public CourseDTO getCourseDetail(Long id) {
        CourseDTO courseDTO = courseDAO.getCourseDetail(id);
        courseDTO.setPaths(pathDAO.getCourseDetail(id));
        courseDTO.setExcludeContents(volunteerExcludeDAO.getCourseDetail(id));
        courseDTO.setIncludeContents(volunteerIncludeDAO.getCourseDetail(id));
        courseDTO.setPrepareContents(volunteerPrepareDAO.getCourseDetail(id));
        courseDTO.setScheduleContents(volunteerScheduleDAO.getCourseDetail(id));
        return courseDTO;
    }

    // 타입별 코스 조회
    public CourseDTO getCourseTypeDetail(String courseType) {
        CourseDTO courseDTO = courseDAO.getCourseTypeDetail(courseType);
        courseDTO.setPaths(pathDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setExcludeContents(volunteerExcludeDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setIncludeContents(volunteerIncludeDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setPrepareContents(volunteerPrepareDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setScheduleContents(volunteerScheduleDAO.getCourseDetail(courseDTO.getId()));

        return courseDTO;
    }

    // 코스 수정
    public void putCourseDetail(CourseDTO courseDTO) {

        courseDAO.putCourseDetail(courseDTO.toCourseVO());
        if (courseDTO.getVolunteerStartDate() != null && courseDTO.getVolunteerStartDate() != "") {
            volunteerDAO.putCourseDetail(courseDTO.toVolunteerVO());
        }
        pathDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getPaths() != null){
            courseDTO.getPaths().forEach(path -> {
                path.setCourseId(courseDTO.getId());
                pathDAO.postAddCourse(path);
            });
        }

        volunteerExcludeDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getExcludeContents() != null){
            courseDTO.getExcludeContents().forEach(content -> {
                volunteerExcludeDAO.postAddCourse(content, courseDTO.getId());
            });
        }
        volunteerIncludeDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getIncludeContents() != null){
            courseDTO.getIncludeContents().forEach(content -> {
                volunteerIncludeDAO.postAddCourse(content, courseDTO.getId());
            });
        }

        volunteerPrepareDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getPrepareContents() != null){
            courseDTO.getPrepareContents().forEach(content -> {
                volunteerPrepareDAO.postAddCourse(content, courseDTO.getId());
            });
        }

        volunteerScheduleDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getScheduleContents() != null){
            courseDTO.getScheduleContents().forEach(content -> {
                log.info("schedult insert "+courseDTO.getId()+" "+content);
                volunteerScheduleDAO.postAddCourse(content, courseDTO.getId());
            });
        }

    }

    // 코스 삭제 :: delete on cascade 안될 경우를 대비해서 모두 삭제
    public void deleteCourseDetail(Long id) {
        courseDAO.deleteCourseDetail(id);
        volunteerDAO.deleteCourseDetail(id);
        pathDAO.deleteCourseDetail(id);
        volunteerExcludeDAO.deleteCourseDetail(id);
        volunteerIncludeDAO.deleteCourseDetail(id);
        volunteerPrepareDAO.deleteCourseDetail(id);
        volunteerScheduleDAO.deleteCourseDetail(id);
    }

    // 신고 목록  :: 전체, 피드, 댓글에 따라 조회를 달리 처리
    public List<ReportListDTO> getReportList(Pagination pagination, Search search) {
        List<ReportListDTO> list = null;
        if (search.getIsAct().equals("FEED")){
            pagination.create(feedReportDAO.getCountAll(search));
            list = feedReportDAO.getReportList(pagination, search);
        } else if (search.getIsAct().equals("REPLY")){
            pagination.create(replyReportDAO.getCountAll(search));
            list = replyReportDAO.getReportList(pagination, search);
        } else {
            pagination.create(reportDAO.getCountAll(search));
            list = reportDAO.getReportList(pagination, search);
        }

        return list;
    }

    public ReplyDetailDTO getReportDetail(Long id, String source) {
        ReplyDetailDTO report = null;
        if (source.equals("FEED")) {
            report = feedDAO.getReportDetail(id);
        } else if (source.equals("REPLY")) {
            report = replyDAO.getReportDetail(id);
        }

        return report;
    }

    public List<NoticeListDTO> getNoticeList(Pagination pagination, Search search) {
        pagination.create(noticeDAO.getCountAll(search));
        return noticeDAO.getNoticeList(pagination, search);
    }

    public NoticeVO getNoticeDetail(Long id) {
        return noticeDAO.getNoticeDetail(id);
    }

    public void putNoticeDetail(NoticeVO notice) {
        noticeDAO.putNoticeDetail(notice);
    }

    public void deleteNoticeDetail(Long id) {
        noticeDAO.deleteNoticeDetail(id);
    }

    public void postNoticeDetail(NoticeVO notice) {

        noticeDAO.postNoticeDetail(notice);
    }

    // Admin 로그인 (Service)
    public Optional<AdminVO> adminLogin(AdminVO adminVO) {
        return adminDAO.findByAdminAccountAndPassword(adminVO);
    }

    public List<VolunteerParticipantDTO> getParticipantsList(Long courseId, PaginationParticipants pagination) {
        pagination.create(volunteerParticipantDAO.getCountAll(courseId));
        return volunteerParticipantDAO.getParticipantsList(courseId, pagination);
    }
}
