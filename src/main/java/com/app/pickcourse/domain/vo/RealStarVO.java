package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RealStarVO {
    @EqualsAndHashCode.Include
    private Long id;

    private int realStar;
    private Long feedId;
}
