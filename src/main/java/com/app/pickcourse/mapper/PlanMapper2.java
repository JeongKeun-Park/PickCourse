package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.TourListDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.util.PaginationOnePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanMapper2 {

    List<TourListDTO> getTourList(Long memberId, @Param("pagination") PaginationOnePage pagination);

    int getCountAllByMemberId(Long memberId);
}
