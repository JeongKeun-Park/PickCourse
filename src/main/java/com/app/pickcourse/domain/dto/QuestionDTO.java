package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.QuestionVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionDTO {
    @EqualsAndHashCode.Include
    Long id;
    String questionContent;
    Long memberId;
    Long planId;
    String createDate;
    String memberNickname;

    public QuestionVO toVO() {
        QuestionVO questionVO = new QuestionVO();

        questionVO.setId(id);
        questionVO.setContent(questionContent);
        questionVO.setMemberId(memberId);
        questionVO.setPlanId(planId);
        questionVO.setCreateDate(createDate);

        return questionVO;
    }
}
