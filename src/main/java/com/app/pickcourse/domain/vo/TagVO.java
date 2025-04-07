package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TagVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String tagContent;
    private Long feedId;
}
