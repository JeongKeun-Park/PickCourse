package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.CourseSelectDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CourseServiceTests {
    @Autowired
    private CourseService courseService;

    @Test
    public void findCourseByIdTest() {
        CourseSelectDTO course = new CourseSelectDTO();
        course = courseService.findCourseById(46L);
        course.getPlans().forEach(plan -> {
            plan.getFeedList().forEach(feed -> {log.info(feed.toString());});
        });
    }

}
