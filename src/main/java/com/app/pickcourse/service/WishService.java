package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.WishPaginationDTO;
import com.app.pickcourse.domain.dto.WishPlanCourseDTO;
import com.app.pickcourse.domain.vo.WishVO;
import com.app.pickcourse.repository.WishDAO;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class WishService {

    private final WishDAO wishDAO;

    public int getTotalWishCount(Long memberId) {
        return wishDAO.getTotalWishCount(memberId);
    }

    public List<WishPlanCourseDTO> selectPagedWishedPlansWithCourseImage(Long memberId, Pagination pagination) {
        return wishDAO.selectPagedWishedPlansWithCourseImage(memberId, pagination);
    }

    public WishPaginationDTO getWishList(Long memberId, Pagination pagination) {
        int total = wishDAO.getWishTotal(memberId);
        pagination.create(total);

        List<WishPlanCourseDTO> list = wishDAO.getWishList(memberId, pagination);

        WishPaginationDTO dto = new WishPaginationDTO();
        dto.setPagination(pagination);
        dto.setWishList(list);

        return dto;
    }

    public void removeWish(Long memberId, Long planId) {
        wishDAO.deleteWish(memberId, planId);
    }

    public void addWish(Long memberId, Long planId) {
        WishVO wishVO = new WishVO();
        wishVO.setMemberId(memberId);
        wishVO.setPlanId(planId);
        wishDAO.insertWish(wishVO);
    }

    public boolean isWished(Long memberId, Long planId) {
        return wishDAO.isWished(memberId, planId);
    }

    public int getWishCountByPlanId(Long planId) {
        return wishDAO.countWishByPlanId(planId);
    }
}
