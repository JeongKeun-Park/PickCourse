package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdminVO {
    @EqualsAndHashCode.Include
    private Long id;

    private String adminAccount;
    private String adminPassword;
    private String adminName;
    private String adminPhone;
    private String adminEmail;
    private String createdDate;
    private String updatedDate;
}
