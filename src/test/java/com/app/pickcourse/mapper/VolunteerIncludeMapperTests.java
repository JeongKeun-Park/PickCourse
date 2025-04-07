package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerIncludeVO;
import com.app.pickcourse.domain.vo.VolunteerIncludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerIncludeMapperTests {
    @Autowired
    private VolunteerIncludeMapper mapper;

    @Test
    public void postAddCourse() {
        mapper.postAddCourse("meal", 129l);
    }

    @Test
    public void getCourseDetail() {
        List<String> volunteerInclude = mapper.getCourseDetail(129l);
        volunteerInclude.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setIncludeContents(volunteerInclude);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteCourseDetail() {
        mapper.deleteCourseDetail(129l);
    }
}
