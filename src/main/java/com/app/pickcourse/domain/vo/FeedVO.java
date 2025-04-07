package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeedVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
}