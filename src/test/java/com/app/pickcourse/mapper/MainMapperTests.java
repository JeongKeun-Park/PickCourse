package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.CourseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MainMapperTests {
    @Autowired
    private MainMapper mainMapper;

    @Test
    public void testSelectByCourseType() {
        List<MainCourseDTO> courses = mainMapper.selectByCourseType();
        log.info(courses.toString());
    }

    @Test
    public void testSelectFeeds() {
        List<MainFeedListDTO> feeds = mainMapper.selectFeeds();
        log.info(feeds.toString());
    }

    @Test
    public void testSelectById() {
        log.info(mainMapper.selectById(1L).toString());
    }

    @Test
    public void testSelectCount() {
        log.info("result = {}", mainMapper.selectCount("D 코스"));
    }

    @Test
    public void testSelectVolunteer() {
        log.info("result = {}", mainMapper.selectVolunteer());
    }
}
