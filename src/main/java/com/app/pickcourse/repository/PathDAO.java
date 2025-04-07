package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.PathVO;
import com.app.pickcourse.mapper.PathMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PathDAO {
    private final PathMapper pathMapper;

    public void postAddCourse(PathVO path) {
        pathMapper.postAddCourse(path);
    }

    public List<PathVO> getCourseDetail(Long id) {
        return pathMapper.getCourseDetail(id);
    }

    public void deleteCourseDetail(Long id) {
        pathMapper.deleteCourseDetail(id);
    }

}
