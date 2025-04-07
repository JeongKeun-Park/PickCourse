package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerScheduleMapperTests {

    @Autowired
    private VolunteerScheduleMapper mapper;

    @Test
    void postAddCourse() {
        mapper.postAddCourse("07:00 work", 129l);
    }


    @Test
    public void getCourseDetail() {
        List<String> volunteerSchedule = mapper.getCourseDetail(129l);
        volunteerSchedule.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setScheduleContents(volunteerSchedule);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteCourseDetail() {
        mapper.deleteCourseDetail(129l);
    }
}
