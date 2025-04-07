package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AnswerMapperTests {
    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void testInsertAnswer() {
        AnswerVO answerVO = new AnswerVO();

        answerVO.setAnswerContent("test");
        answerVO.setMemberId(1L);
        answerVO.setQuestionId(8L);
        answerVO.setPlanId(113L);

        answerMapper.insertAnswer(answerVO);
    }

    @Test
    public void testSelectAnswer() {
        log.info(answerMapper.selectAnswer(127L,8L).toString());
    }
}
