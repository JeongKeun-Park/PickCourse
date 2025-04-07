package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.PayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PayMapperTests {
    @Autowired
    private PayMapper payMapper;

    @Test
    public void testInsertPay() {
        PayVO payVO = new PayVO();

        payVO.setPayPrice(10000);
        payVO.setPayMethod("카카오페이");
        payVO.setMemberId(1L);
        payVO.setPlanId(22L);

        payMapper.insertKakaoPay(payVO);
    }

}
