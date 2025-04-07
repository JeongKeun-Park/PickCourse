package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.TourListDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.mapper.PlanMapper;
import com.app.pickcourse.mapper.PlanMapper2;
import com.app.pickcourse.util.PaginationOnePage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanDAO2 {
    private final PlanMapper2 planMapper;

    public List<TourListDTO> getTourList(Long memberId, PaginationOnePage pagination) {
        return planMapper.getTourList(memberId, pagination);

    }

    public int getCountAllByMemberId(Long memberId) {
        return planMapper.getCountAllByMemberId(memberId);
    }
}
