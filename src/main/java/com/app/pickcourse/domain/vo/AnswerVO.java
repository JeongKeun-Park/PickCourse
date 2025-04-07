package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter@Setter@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnswerVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String answerContent;
    private Long memberId;
    private Long questionId;
    private Long planId;
}
