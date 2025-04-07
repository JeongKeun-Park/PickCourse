package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.RealFeedMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class RealFeedDAO {
    private final RealFeedMapper realFeedMapper;

//    여행후기작성
    public void saveReview(FeedVO feedVO, RealFeedDTO realFeedDTO) {
        realFeedMapper.insertReview(feedVO, realFeedDTO);
        }

    public void postFeedWrite(Long id, Long memberId, Long planId) {
        realFeedMapper.postFeedWrite(id, memberId, planId);
    }

    public RealDTO getRealModify(Long id) {
        return realFeedMapper.getRealModify(id).orElseThrow(()-> new RuntimeException("RealDTO not found"));
    }

    public void deleteRealModify(Long id) {
        realFeedMapper.deleteRealModify(id);
    }

    public List<FeedListDTO> getFeedList() {
        return realFeedMapper.getFeedList();
    }

    public List<FeedListDTO> getMyFeedList(Long memberId) {
        return realFeedMapper.getMyFeedList(memberId);
    }

//   플랜ID로 후기 조회
    public List<FeedListDTO> findFeedListByPlanId(Long planId) {
        return realFeedMapper.selectFeedListByPlanId(planId);
    }

//    카운트
    public int findFeedCountByPlanId(Long planId) {
        return realFeedMapper.selectFeedCount(planId);
    }

//    페이지네이션
    public List<FeedListDTO> findPaginationByPlanId(Pagination pagination, Long planId) {
        return realFeedMapper.selectPaginationByPlanId(pagination, planId);
    }
}
