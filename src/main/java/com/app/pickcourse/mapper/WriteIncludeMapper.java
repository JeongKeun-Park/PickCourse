package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WriteIncludeDTO;
import com.app.pickcourse.domain.vo.WriteIncludeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriteIncludeMapper {
//    추가
    public void insert(WriteIncludeVO writeIncludeVO);
    //    플랜아이디로 조회
    public List<WriteIncludeDTO> selectByPlanId(Long planId);
//    수정
    public void update(WriteIncludeVO writeIncludeVO);
//    삭제
    public void delete(Long id);
}
