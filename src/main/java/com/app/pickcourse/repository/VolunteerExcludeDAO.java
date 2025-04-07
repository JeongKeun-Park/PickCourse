package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.VolunteerExcludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VolunteerExcludeDAO {
    private final VolunteerExcludeMapper volunteerExcludeMapper;

    public void postAddCourse(String content, Long volunteerId) {
        volunteerExcludeMapper.postAddCourse(content, volunteerId);
    }

    public List<String> getCourseDetail(Long volunteerId) {
        return volunteerExcludeMapper.getCourseDetail(volunteerId);
    }

    public void deleteCourseDetail(Long id) {
        volunteerExcludeMapper.deleteCourseDetail(id);
    }
}
