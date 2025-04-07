package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ApplicantDTO;
import com.app.pickcourse.domain.dto.ParticipantDTO;
import com.app.pickcourse.domain.dto.RecentCourseDTO;
import com.app.pickcourse.domain.dto.VolunteerParticipantDTO;
import com.app.pickcourse.domain.vo.ParticipantVO;
import com.app.pickcourse.mapper.ParticipantMapper;
import com.app.pickcourse.mapper.VolunteerParticipantMapper;
import com.app.pickcourse.util.PaginationParticipants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VolunteerParticipantDAO {
    private final VolunteerParticipantMapper volunteerParticipantMapper;

    public List<VolunteerParticipantDTO> getParticipantsList(Long courseId, PaginationParticipants pagination) {
        return volunteerParticipantMapper.getParticipantsList(courseId, pagination);
    }

    public int getCountAll(Long courseId) {
        return volunteerParticipantMapper.getCountAll(courseId);
    }

    public int checkDuplicate(Long courseId, Long memberId) {
        return volunteerParticipantMapper.checkDuplicate(courseId, memberId);
    }

    public void postEcoparticipant(Long courseId, Long memberId) {
        volunteerParticipantMapper.postEcoparticipant(courseId, memberId);
    }
}
