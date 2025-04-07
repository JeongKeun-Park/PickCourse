package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.MyPayListDTO;
import com.app.pickcourse.domain.vo.PayVO;
import com.app.pickcourse.mapper.PayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PayDAO {
    private final PayMapper payMapper;

    public void saveKakaoPay(PayVO payVO) {
        payMapper.insertKakaoPay(payVO);
    }

    public List<MyPayListDTO> findByMemberId(Long memberId) {
        return payMapper.selectMyPayList(memberId);
    }

}
