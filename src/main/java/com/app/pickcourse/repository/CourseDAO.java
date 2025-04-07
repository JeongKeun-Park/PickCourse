package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.mapper.CourseMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseDAO {
    private final CourseMapper courseMapper;

    public void putCourseDetail(CourseVO courseVO) {
        courseMapper.putCourseDetail(courseVO);
    }

    public int getCountAll(Search search) {
        return courseMapper.getCountAll(search);
    };

    public void postAddCourse(CourseDTO courseDTO) {
        courseMapper.postAddCourse(courseDTO);
    }

    public List<CourseListDTO> getCourseList(Pagination pagination, Search search) {
        return courseMapper.getCourseList(pagination,search);
    }

    public void patchCourseListExpire(String courseType) {
        courseMapper.patchCourseListExpire(courseType);
    }

    public void patchCourseListRegist(Long id, String courseType) {
        courseMapper.patchCourseListRegist(id, courseType);
    }

    public CourseDTO getCourseDetail(Long id) {
        return courseMapper.getCourseDetail(id).orElseThrow(() -> new RuntimeException("Course Detail Not Found"));
    }

    public CourseDTO getCourseTypeDetail(String courseType) {
        return courseMapper.getCourseTypeDetail(courseType).orElseThrow(() -> new RuntimeException("Course Type Detail Not Found"));
    }

    public void deleteCourseDetail(Long id) {
        courseMapper.deleteCourseDetail(id);
    }

    public Optional<CourseSelectDTO> findCourseViewById(Long id) {
        return courseMapper.selectCourseViewById(id);
    }

    // 봉사코스 정보 가져오기
    public CourseDTO getEco(Long id) {
        return courseMapper.getEco(id);
    }
}
