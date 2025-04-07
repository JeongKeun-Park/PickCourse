package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScheduleVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String scheduleContent;
    private Long planId;
    private String status;
}
