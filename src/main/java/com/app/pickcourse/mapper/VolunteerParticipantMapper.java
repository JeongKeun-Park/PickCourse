package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ApplicantDTO;
import com.app.pickcourse.domain.dto.ParticipantDTO;
import com.app.pickcourse.domain.dto.RecentCourseDTO;
import com.app.pickcourse.domain.dto.VolunteerParticipantDTO;
import com.app.pickcourse.domain.vo.ParticipantVO;
import com.app.pickcourse.util.PaginationParticipants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerParticipantMapper {

    List<VolunteerParticipantDTO> getParticipantsList(@Param("courseId") Long courseId, @Param("pagination") PaginationParticipants pagination);

    int getCountAll(Long courseId);

    int checkDuplicate(@Param("courseId")Long courseId, @Param("memberId") Long memberId);

    void postEcoparticipant(@Param("courseId") Long courseId, @Param("memberId") Long memberId);
}
