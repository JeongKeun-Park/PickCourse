package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WriteIncludeVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String includeContent;
    private Long planId;
}
