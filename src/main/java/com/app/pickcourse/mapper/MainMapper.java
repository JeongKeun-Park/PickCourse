package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
//    타입이있는 코스 조회
    public List<MainCourseDTO> selectByCourseType();

//    최근 피드목록 3개 조회
    public List<MainFeedListDTO> selectFeeds();

//    멤버정보 조회
    public MemberVO selectById(Long id);

//    카운팅
    public Integer selectCount(String type);

//    봉사코스조회
    public MainCourseDTO selectVolunteer();
}
