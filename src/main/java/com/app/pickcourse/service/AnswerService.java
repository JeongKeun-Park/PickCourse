package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.AnswerDTO;
import com.app.pickcourse.domain.vo.AnswerVO;
import com.app.pickcourse.repository.AnswerDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AnswerService {
    private final AnswerDAO answerDAO;

    public void answerAdd(AnswerDTO answerDTO) {
        AnswerVO answerVO = answerDTO.toVO();

        answerDAO.saveAnswer(answerVO);
    }

    public AnswerDTO getAnswerList(Long planId,Long questionId) {
        return answerDAO.findAllAnswer(planId,questionId);
    }

}
