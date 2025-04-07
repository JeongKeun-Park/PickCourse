package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionVO {
    @EqualsAndHashCode.Include
    Long id;
    String content;
    Long memberId;
    Long planId;
    String createDate;
}
