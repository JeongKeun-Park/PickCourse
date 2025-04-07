package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MainDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@SpringBootTest
@Slf4j
public class MainServiceTests {
    @Autowired
    private MainService mainService;

    @Test
    public void testByCourseType() {
        MainDTO mainDTO = new MainDTO();
        mainDTO = mainService.getCourses();
        log.info(mainDTO.getCourses().toString());
    }
}
