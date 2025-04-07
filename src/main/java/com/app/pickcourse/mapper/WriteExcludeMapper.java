package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WriteExcludeDTO;
import com.app.pickcourse.domain.vo.WriteExcludeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriteExcludeMapper {
//    추가
    public void insert(WriteExcludeVO writeExcludeVO);
//    플랜아이디로 조회
    public List<WriteExcludeDTO> selectByPlanId(Long planId);
//    수정
    public void update(WriteExcludeVO writeExcludeVO);
//    삭제
    public void delete(Long id);
}
