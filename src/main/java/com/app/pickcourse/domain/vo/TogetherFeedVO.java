package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TogetherFeedVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
}
