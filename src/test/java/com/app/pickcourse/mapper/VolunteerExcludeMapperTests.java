package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerExcludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerExcludeMapperTests {
    @Autowired
    private VolunteerExcludeMapper mapper;

    @Test
    public void postAddCourse() {

        mapper.postAddCourse("money", 129l);

    }

    @Test
    public void getCourseDetail() {
        List<String> excludeContents = mapper.getCourseDetail(129l);
        excludeContents.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setExcludeContents(excludeContents);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteCourseDetail() {
        mapper.deleteCourseDetail(129l);
    }
}
