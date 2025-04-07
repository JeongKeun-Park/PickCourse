package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.AnswerDTO;
import com.app.pickcourse.domain.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {
//    추가
    public void insertAnswer(AnswerVO answerVO);

//    전체조회
    public AnswerDTO selectAnswer(Long planId, Long questionId);
}
