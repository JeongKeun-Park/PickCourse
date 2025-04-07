package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RealFileVO {
    @EqualsAndHashCode.Include
        private Long id;
        private Long RealFeedId;
}
