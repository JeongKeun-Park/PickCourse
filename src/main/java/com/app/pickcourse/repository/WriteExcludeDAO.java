package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.WriteExcludeDTO;
import com.app.pickcourse.domain.vo.WriteExcludeVO;
import com.app.pickcourse.mapper.WriteExcludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WriteExcludeDAO {
    private final WriteExcludeMapper writeExcludeMapper;

//    추가
    public void save(WriteExcludeVO writeExcludeVO) {
        writeExcludeMapper.insert(writeExcludeVO);
    }
//    플랜아이디로 조회
    public List<WriteExcludeDTO> findByPlanId(Long id) {
        return writeExcludeMapper.selectByPlanId(id);
    }
//    수정
    public void setWriteExclude(WriteExcludeVO writeExcludeVO) {
        writeExcludeMapper.update(writeExcludeVO);
    }
//    삭제
    public void delete(Long id) {
        writeExcludeMapper.delete(id);
    }
}
