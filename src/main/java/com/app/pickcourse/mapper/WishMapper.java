package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WishPlanCourseDTO;
import com.app.pickcourse.domain.vo.WishVO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WishMapper {
//    찜한목록에 추가
    public void insertWish(WishVO wishVO);

//    찜한 목록
    public WishVO selectByMemberId(Long memberId);

//    삭제
    public void deleteWishByMemberIdAndPlanId(@Param("memberId") Long memberId,
                                                  @Param("planId") Long planId);

    int getTotalWishCount(Long memberId);

    // 페이징된 찜 목록 조회
    List<WishPlanCourseDTO> selectPagedWishedPlansWithCourseImage(Map<String, Object> params);

    List<WishPlanCourseDTO> selectWishList(@Param("memberId") Long memberId, @Param("pagination") Pagination pagination);

    int selectWishTotal(Long memberId);

    int isWished(@Param("memberId") Long memberId, @Param("planId") Long planId);

    int countWishByPlanId(Long planId);

}
