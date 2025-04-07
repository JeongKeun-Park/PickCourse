package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.mapper.FeedReportMapper;
import com.app.pickcourse.mapper.ReportMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedReportDAO {
    private final FeedReportMapper feedReportMapper;

    // 전체 건수 가져오기
    public int getCountAll(Search search) {
        return feedReportMapper.getCountAll(search);
    }

    // 신고 목록
    public List<ReportListDTO> getReportList(Pagination pagination, Search search) {
        return feedReportMapper.getReportList(pagination, search);
    }

    public void postReportFeedList(Long id, Long reportedId, String reportedReason, Long memberId) {
        feedReportMapper.postReportFeedList(id, reportedId, reportedReason, memberId);
    }
}
