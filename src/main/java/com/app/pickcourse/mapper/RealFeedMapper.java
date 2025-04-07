package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RealFeedMapper {

    // postReviewWrite(리얼후기 작성)
    void postFeedWrite(@Param("id") Long id, @Param("memberId") Long memberId, @Param("planId") Long planId);

    Optional<RealDTO> getRealModify(Long id);

    void deleteRealModify(Long id);

    List<FeedListDTO> getFeedList();


    Optional<ReviewDTO> getReviewModify(Long id);

//    플랜후기작성
    public void insertReview(FeedVO feedVO, RealFeedDTO realFeedDTO);

    List<FeedListDTO> getMyFeedList(Long memberId);

//    플랜ID로 후기목록 조회
    public List<FeedListDTO> selectFeedListByPlanId(Long planId);

//    피드갯수 플랜ID로 조회
    public int selectFeedCount(Long planId);

//    플랜ID 페이지네이션
    public List<FeedListDTO> selectPaginationByPlanId(Pagination pagination, Long planId);
}
