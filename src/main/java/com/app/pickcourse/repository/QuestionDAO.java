package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.QuestionDTO;
import com.app.pickcourse.domain.vo.QuestionVO;
import com.app.pickcourse.mapper.QuestionMapper;
import com.app.pickcourse.util.QuestionPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDAO {
    private final QuestionMapper questionMapper;
//    댓글작성
    public void saveQuestion(QuestionVO questionVO) {
        questionMapper.insertQuestion(questionVO);
    }

//    planId의 질문 조회
    public List<QuestionDTO> findQuestion(Long planId) {
        return questionMapper.selectQuestion(planId);
    }

    public int findCountByPlanId(Long planId) {
        return questionMapper.selectCountByPlanId(planId);
    }
}
