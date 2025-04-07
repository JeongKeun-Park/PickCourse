package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.PayVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PayDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private int payPrice;
    private String payMethod;
    private String payCardCompany;
    private Long memberId;
    private Long planId;
    private String createDate;
    private String updateDate;

    public PayVO toVO() {
        PayVO payVO = new PayVO();
        payVO.setId(id);
        payVO.setPayPrice(payPrice);
        payVO.setPayMethod(payMethod);
        payVO.setPayCardCompany(payCardCompany);
        payVO.setMemberId(memberId);
        payVO.setPlanId(planId);
        payVO.setCreateDate(createDate);
        payVO.setUpdateDate(updateDate);
        return payVO;
    }
}
