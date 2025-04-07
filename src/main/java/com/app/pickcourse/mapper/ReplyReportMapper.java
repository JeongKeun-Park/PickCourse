package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyReportMapper {

    int getCountAll(@Param("search") Search search);

    List<ReportListDTO> getReportList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    void postReportReplyList(@Param("id") Long id, @Param("reportedId") Long replyId, @Param("reportedReason") String reportedReason, @Param("memberId") Long memberId);

    void deleteReplyList(Long id);

    List<Long> selectId(@Param("replyId") Long id);
}
