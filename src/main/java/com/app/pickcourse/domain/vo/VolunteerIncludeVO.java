package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VolunteerIncludeVO {
    @EqualsAndHashCode.Include
    private Long   id;

    private String includeContent;
    private Long   volunteerId;
    private String createdDate;
    private String updatedDate;
}