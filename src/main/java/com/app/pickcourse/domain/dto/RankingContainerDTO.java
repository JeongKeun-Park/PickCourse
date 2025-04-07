package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.PlanVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RankingContainerDTO {

    private List<RankingDTO> weekRanking;
    private List<RankingDTO> monthRanking;
    private List<RankingDTO> yearRanking;
}
