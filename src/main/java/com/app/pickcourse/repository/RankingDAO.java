package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.RankingDTO;
import com.app.pickcourse.mapper.RankingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RankingDAO {
    private final RankingMapper rankingMapper;

    public List<RankingDTO> getRankingWeek() {
        return rankingMapper.getRankingWeek();
    }

    public List<RankingDTO> getRankingMonth() {
        return rankingMapper.getRankingMonth();
    }

    public List<RankingDTO> getRankingYear() {
        return rankingMapper.getRankingYear();
    }

    public String getIsWish(Long planId, Long memberId) {
        return rankingMapper.getIsWish(planId, memberId);
    }

    public void postWish(Long planId, Long memberId) {
        rankingMapper.postWish(planId, memberId);
    }

    public void deleteWish(Long planId, Long memberId) {
        rankingMapper.deleteWish(planId, memberId);
    }

    public int getTotalParticipants(Long planId) {
        return rankingMapper.getTotalParticipants(planId);
    };
}
