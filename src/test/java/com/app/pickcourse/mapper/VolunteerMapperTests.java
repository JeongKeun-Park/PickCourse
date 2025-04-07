package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class VolunteerMapperTests {
    @Autowired
    private VolunteerMapper mapper;

    @Test
    public void postAddCourse() {
        VolunteerVO volunteer = new VolunteerVO();
        volunteer.setId(129l);
        volunteer.setVolunteerStartDate(String.valueOf(LocalDate.parse("2025-03-10")));
        volunteer.setVolunteerEndDate(String.valueOf(LocalDate.parse("2025-03-12")));
        volunteer.setVolunteerDeadline(String.valueOf(LocalDate.parse("2025-03-08")));
        mapper.postAddCourse(volunteer);
    }

    @Test
    public void putCourseDetail() {
        VolunteerVO volunteer = new VolunteerVO();
        volunteer.setId(129l);
        volunteer.setVolunteerStartDate(String.valueOf(LocalDate.parse("2025-03-15")));
        volunteer.setVolunteerEndDate(String.valueOf(LocalDate.parse("2025-03-20")));
        volunteer.setVolunteerDeadline(String.valueOf(LocalDate.parse("2025-03-10")));
        mapper.putCourseDetail(volunteer);
    }
}
