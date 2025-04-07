package com.app.pickcourse.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.app.pickcourse.domain.dto.RankingDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RankingMapper {

    // 주간, 월간, 년간 랭킹 가져오기
    List<RankingDTO> getRankingWeek();
    List<RankingDTO> getRankingMonth();
    List<RankingDTO> getRankingYear();

    // 찜 여부 가져오기
    String getIsWish(@Param("planId") Long planId, @Param("memberId") Long memberId);

    // 찜 설정
    void postWish(@Param("planId") Long planId, @Param("memberId") Long memberId);

    // 찜 해제
    void deleteWish(@Param("planId") Long planId, @Param("memberId") Long memberId);

    // 전체 참여자 수 가져오기
    int getTotalParticipants(Long planId);
}
