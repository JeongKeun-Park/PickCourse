package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.ReplyMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.PaginationOnePage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;
    private final FeedMapper feedMapper;

    public ReplyDetailDTO getReportDetail(Long id) {
        return replyMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }

    public int getCountAll(Long feedId) {
        return replyMapper.getCountAll(feedId);
    }

    public List<ReplyListDTO> getReplyList(Long feedId, PaginationOnePage pagination) {
        return replyMapper.getReplyList(feedId, pagination);
    }

    public void deleteReplyList(Long id) {
        replyMapper.deleteReplyList(id);
    }

    public void postReplyList(ReplyVO replyVO) {
        replyMapper.postReplyList(replyVO);
    }

    public String selectTypeOfFeed(Long feedId) {
        return feedMapper.selectTypeOfFeed(feedId);
    }

    public int getMyCountAll(long memberId) {
        return replyMapper.getMyCountAll(memberId);
    }

    public List<ReplyListDTO> getMyReplyList(long memberId, PaginationOnePage pagination) {
        return replyMapper.getMyReplyList(memberId, pagination);
    }
}
