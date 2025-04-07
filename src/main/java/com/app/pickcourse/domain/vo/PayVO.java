package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PayVO {
    @EqualsAndHashCode.Include
    private Long id;
    private int payPrice;
    private String payMethod;
    private String payCardCompany;
    private Long memberId;
    private Long planId;
    private String createDate;
    private String updateDate;
}
