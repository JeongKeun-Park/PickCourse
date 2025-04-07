package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class CourseMapperTests {

    @Autowired
    private CourseMapper mapper;
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void postAddCourseTest() {
        CourseDTO course = new CourseDTO();
        course.setCourseType("");
        course.setCourseIsVolunteer('Y');
        course.setCourseName("남원");
        course.setCourseDistance("1.6km");
        course.setCourseSchedule("3박4일");
        course.setCourseTheme("힐링");
        course.setCourseContent("즐거운 여행");
        course.setCourseFilePath("C:\\temp\\course.png");
        course.setCourseFileSize("2kb");
        course.setCourseFileName("represent");
        course.setAdminId(1l);

        mapper.postAddCourse(course);
    }

    @Test
    public void getCourseTest() {
        Optional<CourseDTO> courseDTO = mapper.getCourseDetail(258L);
        CourseDTO course = courseDTO.orElseThrow(() -> new RuntimeException("CourseDTO not found"));
        log.info(course.toString());
    }

    @Test
    public void getCourseListTest() {
        Search search = new Search();
        search.setType("nwt");
        search.setKeyWord("nat");
        Pagination pagination = new Pagination();
        log.info(pagination.toString());
        List<CourseListDTO> courseList = mapper.getCourseList(pagination, search);
        log.info("courseList size:" + courseList.size());
        courseList.forEach(System.out::println);
    }

    @Test
    public void patchCourseListTest() {
        mapper.patchCourseListRegist(10l, "B");
    }

    @Test
    public void putCourseDetailTest() {
        CourseVO course = new CourseVO();
        course.setId(4l);
        course.setCourseName("서울");
        course.setCourseDistance("3km");
        course.setCourseSchedule("5박6일");
        course.setCourseTheme("자연");
        course.setCourseContent("편안한 휴식");
        course.setCourseFilePath("C:\\temp\\relax.png");
        course.setCourseFileSize("1.5kb");
        course.setCourseFileName("hue");
        mapper.putCourseDetail(course);
    }

    @Test
    public void testSelectCourseViewById() {
        CourseSelectDTO courseSelectDTO = new CourseSelectDTO();
        courseSelectDTO = courseMapper.selectCourseViewById(46L).orElse(null);
        log.info(courseSelectDTO.toString());
    }
}
