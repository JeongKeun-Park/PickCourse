package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlanDetailDTO {
    private MemberVO member;
    private PlanDTO plan;
    private List<FeedListDTO> feedList;
}
