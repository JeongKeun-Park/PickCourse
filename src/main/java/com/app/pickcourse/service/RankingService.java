package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.RankingContainerDTO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.repository.RankingDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RankingService {

    private final RankingDAO rankingDAO;

    public RankingContainerDTO getRanking(Long memberId) {

        RankingContainerDTO rankings = new RankingContainerDTO();
        rankings.setWeekRanking(rankingDAO.getRankingWeek());
        rankings.setMonthRanking(rankingDAO.getRankingMonth());
        rankings.setYearRanking(rankingDAO.getRankingYear());

        if (rankings.getWeekRanking() != null && !rankings.getWeekRanking().isEmpty()) { // 총 참여자 수 가져오기
            rankings.getWeekRanking().forEach( rankingDTO -> {
                rankingDTO.setParticipants(rankingDAO.getTotalParticipants(rankingDTO.getId()));
            });
        }
        if (rankings.getMonthRanking() != null && !rankings.getMonthRanking().isEmpty()) { // 총 참여자 수 가져오기
            rankings.getMonthRanking().forEach( rankingDTO -> {
                rankingDTO.setParticipants(rankingDAO.getTotalParticipants(rankingDTO.getId()));
            });
        }
        if (rankings.getYearRanking() != null && !rankings.getYearRanking().isEmpty()) { // 총 참여자 수 가져오기
            rankings.getYearRanking().forEach( rankingDTO -> {
                rankingDTO.setParticipants(rankingDAO.getTotalParticipants(rankingDTO.getId()));
            });
        }

        if (memberId != null) { // 로그인 했을 경우 찜 여부 가져오기
            rankings.getWeekRanking().forEach(rw -> {
               rw.setIsWish(rankingDAO.getIsWish(rw.getId(), memberId));
            });
            rankings.getMonthRanking().forEach(rm -> {
                rm.setIsWish(rankingDAO.getIsWish(rm.getId(),memberId));
            });
            rankings.getYearRanking().forEach(ry -> {
                ry.setIsWish(rankingDAO.getIsWish(ry.getId(),memberId));
            });
        }

        return rankings;
    }

    public void postWish(Long planId, Long memberId) {
        rankingDAO.postWish(planId, memberId);
    }

    public void deleteWish(Long planId, Long memberId) {
        rankingDAO.deleteWish(planId, memberId);
    }

}
