package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long planId;
    private String createdDate;
    private String updatedDate;
}
