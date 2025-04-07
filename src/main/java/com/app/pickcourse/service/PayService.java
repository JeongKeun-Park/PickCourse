package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MyPayListDTO;
import com.app.pickcourse.domain.dto.PayDTO;
import com.app.pickcourse.domain.vo.PayVO;
import com.app.pickcourse.repository.PayDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PayService {
    private final PayDAO payDAO;

    public void addPay(PayDTO payDTO) {
        PayVO payVO = payDTO.toVO();

        payDAO.saveKakaoPay(payVO);
    }

    public List<MyPayListDTO> getMyPayList(Long memberId) {
        return payDAO.findByMemberId(memberId);
    }

}
