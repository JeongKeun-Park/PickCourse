package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WishVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String createdDate;
    private String updatedDate;
    private Long memberId;
    private Long planId;
}
