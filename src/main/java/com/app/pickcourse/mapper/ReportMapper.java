package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.domain.vo.ReportIdVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {

    int getCountAll(@Param("search") Search search);

    List<ReportListDTO> getReportList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    void postReportReplyList(ReportIdVO reportIdVO);

    void deleteReplyList(Long id);
}
