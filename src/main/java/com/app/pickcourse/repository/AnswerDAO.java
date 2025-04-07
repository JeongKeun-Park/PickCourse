package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.AnswerDTO;
import com.app.pickcourse.domain.vo.AnswerVO;
import com.app.pickcourse.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDAO {
    private final AnswerMapper answerMapper;

    public void saveAnswer(AnswerVO answerVO) {
        answerMapper.insertAnswer(answerVO);
    }

    public AnswerDTO findAllAnswer(Long planId,Long questionId) {
        return answerMapper.selectAnswer(planId, questionId);
    }

}
