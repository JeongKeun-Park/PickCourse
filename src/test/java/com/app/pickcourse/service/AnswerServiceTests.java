package com.app.pickcourse.service;

import com.app.pickcourse.domain.vo.AnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {
    @Autowired
    private AnswerService answerService;

    @Test
    public void testFindAnswerByQuestionId() {
        answerService.getAnswerList(127L,8L);
    }
}
