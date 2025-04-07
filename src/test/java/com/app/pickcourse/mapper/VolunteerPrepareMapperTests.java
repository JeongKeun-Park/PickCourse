package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerPrepareVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerPrepareMapperTests {

    @Autowired
    private VolunteerPrepareMapper mapper;

    @Test
    void postAddCourse() {
        mapper.postAddCourse("towels", 129l);
    }


    @Test
    public void getCourseDetail() {
        List<String> volunteerPrepare = mapper.getCourseDetail(129l);
        volunteerPrepare.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setPrepareContents(volunteerPrepare);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteCourseDetail() {
        mapper.deleteCourseDetail(129l);
    }
}
