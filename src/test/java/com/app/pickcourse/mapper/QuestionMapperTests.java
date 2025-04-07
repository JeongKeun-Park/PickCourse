package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.QuestionDTO;
import com.app.pickcourse.domain.vo.QuestionVO;
import com.app.pickcourse.util.QuestionPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionMapperTests {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    private PlanMapper planMapper;

    @Test
    public void testInsertQuestion() {
        QuestionVO questionVO = new QuestionVO();

        questionVO.setContent("테스트1");
        questionVO.setMemberId(1L);
        questionVO.setPlanId(113L);

        questionMapper.insertQuestion(questionVO);
    }

    @Test
    public void testSelectQuestion() {
        List<QuestionDTO> lists = questionMapper.selectQuestion(113L);
        lists.forEach((list)->log.info(list.toString()));
    }

    @Test
    public void testSelectQuestionByPlanId() {
        log.info("개수 {}", questionMapper.selectCountByPlanId(113L));
    }
}
