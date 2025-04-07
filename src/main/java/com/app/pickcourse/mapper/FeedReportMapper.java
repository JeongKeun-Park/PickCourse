package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedReportMapper {

    int getCountAll(@Param("search") Search search);

    List<ReportListDTO> getReportList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    void saveFeedReport(@Param("id") Long id, @Param("reportedId") Long feedId, @Param("memberId") Long memberId);

    void postReportFeedList(Long id, Long reportedId, String reportedReason, Long memberId);
}
