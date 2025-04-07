package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.WriteIncludeDTO;
import com.app.pickcourse.domain.vo.WriteIncludeVO;
import com.app.pickcourse.mapper.WriteIncludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WriteIncludeDAO {
    private final WriteIncludeMapper writeIncludeMapper;

//    추가
    public void save(WriteIncludeVO writeIncludeVO) {
        writeIncludeMapper.insert(writeIncludeVO);
    }
//    플랜아이디로 조회
    public List<WriteIncludeDTO> findByPlanId(Long planId) {
        return writeIncludeMapper.selectByPlanId(planId);
    }
//    수정
    public void setWriteInclude(WriteIncludeVO writeIncludeVO) {
        writeIncludeMapper.update(writeIncludeVO);
    }
//    삭제
    public void delete(Long planId) {
        writeIncludeMapper.delete(planId);
    }
}
