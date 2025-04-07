package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.VolunteerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VolunteerMapper {

    // 추천코스 등록 :: 봉사코스 일 경우
    void postAddCourse(VolunteerVO volunteerVO);

    // 추천코스 수정
    void putCourseDetail(VolunteerVO volunteerVO);

    void deleteCourseDetail(Long id);
}
