package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.MainCourseDTO;
import com.app.pickcourse.domain.dto.MainFeedListDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainDAO {
    private final MainMapper mainMapper;

    public List<MainCourseDTO> findByCourseType() {
        return mainMapper.selectByCourseType();
    }

    public List<MainFeedListDTO> findFeeds() {
        return mainMapper.selectFeeds();
    }

    public MemberVO selectById(Long id){
        return mainMapper.selectById(id);
    }

    public Integer findCount(String type){
        return mainMapper.selectCount(type);
    }

    public MainCourseDTO findVolunteer(){
        return mainMapper.selectVolunteer();
    }
}
