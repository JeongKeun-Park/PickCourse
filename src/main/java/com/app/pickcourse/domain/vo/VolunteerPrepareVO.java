package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VolunteerPrepareVO {
    @EqualsAndHashCode.Include
    private Long   id;

    private String prepareContent;
    private Long   volunteerId;
    private String createdDate;
    private String updatedDate;
}