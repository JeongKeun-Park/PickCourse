package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.VolunteerScheduleVO;
import com.app.pickcourse.mapper.VolunteerPrepareMapper;
import com.app.pickcourse.mapper.VolunteerScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VolunteerScheduleDAO {
    private final VolunteerScheduleMapper volunteerScheduleMapper;

    public void postAddCourse(String content, Long volunteerId) {
        volunteerScheduleMapper.postAddCourse(content, volunteerId);
    }

    public List<String> getCourseDetail(Long id) {
        return volunteerScheduleMapper.getCourseDetail(id);
    }

    public void deleteCourseDetail(Long volunteerId) {
        volunteerScheduleMapper.deleteCourseDetail(volunteerId);
    }
}
