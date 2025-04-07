package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PathVO {
    @EqualsAndHashCode.Include
    private Long   id;

    private String pathName;
    private String pathAddress;
    private Long   courseId;
    private String createdDate;
    private String updatedDate;
}
