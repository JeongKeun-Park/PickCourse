package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.WishPlanCourseDTO;
import com.app.pickcourse.domain.vo.WishVO;
import com.app.pickcourse.mapper.WishMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class WishDAO {

    private final WishMapper wishMapper;

    public void insertWish(WishVO wishVO) {
        wishMapper.insertWish(wishVO);
    }

    public int getTotalWishCount(Long memberId) {
        return wishMapper.getTotalWishCount(memberId);
    }

    public List<WishPlanCourseDTO> selectPagedWishedPlansWithCourseImage(Long memberId, Pagination pagination) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("startRow", pagination.getStartRow());
        params.put("endRow", pagination.getEndRow());
        return wishMapper.selectPagedWishedPlansWithCourseImage(params);
    }

    public List<WishPlanCourseDTO> getWishList(Long memberId, Pagination pagination) {
        return wishMapper.selectWishList(memberId, pagination);
    }

    public int getWishTotal(Long memberId) {
        return wishMapper.selectWishTotal(memberId);
    }

    public void deleteWish(Long memberId, Long planId) {
        wishMapper.deleteWishByMemberIdAndPlanId(memberId, planId);
    }

    public boolean isWished(Long memberId, Long planId) {
        return wishMapper.isWished(memberId, planId) > 0;
    }

    public int countWishByPlanId(Long planId) {
        return wishMapper.countWishByPlanId(planId);
    }

}
