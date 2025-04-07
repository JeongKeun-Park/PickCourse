package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.domain.vo.ReportIdVO;
import com.app.pickcourse.mapper.PlanMapper;
import com.app.pickcourse.mapper.ReportMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportDAO {
    private final ReportMapper reportMapper;

    // 전체 건수 가져오기
    public int getCountAll(Search search) {
        return reportMapper.getCountAll(search);
    }

    // 신고 목록
    public List<ReportListDTO> getReportList(Pagination pagination, Search search) {
        return reportMapper.getReportList(pagination, search);
    }

    public void postReportReplyList(ReportIdVO reportIdVO) {
        reportMapper.postReportReplyList(reportIdVO);
    }

    public void deleteReplyList(Long id) {
        reportMapper.deleteReplyList(id);
    }
}
