package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TogetherReplyVO {
    @EqualsAndHashCode.Include
    private Long id;

    private Long memberId;
    private Long TogetherFeedId;
}

