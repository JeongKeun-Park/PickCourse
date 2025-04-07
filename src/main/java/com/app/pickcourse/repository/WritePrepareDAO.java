package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.WritePrepareDTO;
import com.app.pickcourse.domain.vo.WritePrepareVO;
import com.app.pickcourse.mapper.WritePrepareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WritePrepareDAO {
    private final WritePrepareMapper writePrepareMapper;

//    추가
    public void save(WritePrepareVO writePrepareVO) {
        writePrepareMapper.insert(writePrepareVO);
    }
//    플랜아이디로 조회
    public List<WritePrepareDTO> findByPlanId(Long planId) {
        return writePrepareMapper.selectByPlanId(planId);
    }
//    수정
    public void setWritePrepare(WritePrepareVO writePrepareVO) {
        writePrepareMapper.update(writePrepareVO);
    }
//    삭제
    public void delete(Long Id) {
        writePrepareMapper.delete(Id);
    }
}
