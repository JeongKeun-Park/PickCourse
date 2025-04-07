package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.mapper.FeedReportMapper;
import com.app.pickcourse.mapper.ReplyReportMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyReportDAO {
    private final ReplyReportMapper replyReportMapper;

    // 전체 건수 가져오기
    public int getCountAll(Search search) {
        return replyReportMapper.getCountAll(search);
    }

    // 신고 목록
    public List<ReportListDTO> getReportList(Pagination pagination, Search search) {
        return replyReportMapper.getReportList(pagination, search);
    }

    public void postReportReplyList(Long id, Long reportedId, String reportedReason, Long memberId){
        replyReportMapper.postReportReplyList(id, reportedId, reportedReason, memberId);
    }

    public void deleteReplyList(Long id) {
        replyReportMapper.deleteReplyList(id);
    }

    public List<Long> selectId(Long id) {
        return replyReportMapper.selectId(id);
    }
}
