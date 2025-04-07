package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.PayDTO;
import com.app.pickcourse.domain.vo.PayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PayServiceTests {
    @Autowired
    private PayService payService;

    @Test
    public void testAddapy() {
        PayDTO payDTO = new PayDTO();

        payDTO.setPayPrice(10000);
        payDTO.setPayMethod("카카오페이");
        payDTO.setMemberId(1L);
        payDTO.setPlanId(22L);

        log.info(payDTO.toString());

        payService.addPay(payDTO);
    }
}
