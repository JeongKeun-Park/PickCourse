package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.AnswerVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter@Setter@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnswerDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String answerContent;
    private Long memberId;
    private Long questionId;
    private String memberNickname;
    private Long planId;

    public AnswerVO toVO() {
        AnswerVO answerVO = new AnswerVO();
        answerVO.setId(id);
        answerVO.setAnswerContent(answerContent);
        answerVO.setMemberId(memberId);
        answerVO.setQuestionId(questionId);
        answerVO.setPlanId(planId);
        return answerVO;

    }
}
