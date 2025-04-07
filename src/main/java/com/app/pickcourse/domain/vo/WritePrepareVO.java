package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WritePrepareVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String prepareContent;
    private Long planId;
}
