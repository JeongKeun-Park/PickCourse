package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.VolunteerExcludeMapper;
import com.app.pickcourse.mapper.VolunteerIncludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VolunteerIncludeDAO {
    private final VolunteerIncludeMapper volunteerIncludeMapper;

    public void postAddCourse(String content, Long volunteerId) {
        volunteerIncludeMapper.postAddCourse(content,volunteerId);
    }

    public List<String> getCourseDetail(Long id) {
        return volunteerIncludeMapper.getCourseDetail(id);
    }

    public void deleteCourseDetail(Long id) {
        volunteerIncludeMapper.deleteCourseDetail(id);
    }
}
