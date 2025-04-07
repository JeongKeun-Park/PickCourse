package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseMapper {

    // 페이지 처리를 위한 전체 화면 갯수
    int getCountAll(@Param("search") Search search);

    // 추천코스 등록
    void postAddCourse(CourseDTO courseDTO);

    // 추천코스 조회
    Optional<CourseDTO> getCourseDetail(Long id);

    // 타입별 추천코스 조회
    Optional<CourseDTO> getCourseTypeDetail(String courseType);

    // 추천코스 목록 조회
    List<CourseListDTO> getCourseList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    // 추천코스 수정
    void putCourseDetail(CourseVO courseVO);

    // 추천코스 타입 정하기 :: 기존에 같은 타입으로 등록된 코스의 타입 클리어
    void patchCourseListExpire(String courseType);

    // 추천코스 타입 정하기 :: 정해진 타입으로 코스 세팅
    void patchCourseListRegist(@Param("id") Long id, @Param("courseType") String courseType);

    void deleteCourseDetail(Long id);

//    회원에게 보여질 코스조회
    public Optional<CourseSelectDTO> selectCourseViewById(Long id);

    // 봉사코스 정보 가져오기
    CourseDTO getEco(Long id);
}
