package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ApplicantDTO;
import com.app.pickcourse.domain.dto.ParticipantDTO;
import com.app.pickcourse.domain.dto.RecentCourseDTO;
import com.app.pickcourse.domain.vo.ParticipantVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParticipantMapper {
//    추가
    public void insert(ParticipantVO participantVO);
//    플랜ID로 조회
    public List<ParticipantDTO> selectByPlanId(Long planId);

    public int getNormalCourseParticipationCount(@Param("memberId") Long memberId);

    public int getVolunteerCourseParticipationCount(@Param("memberId") Long memberId);

    public RecentCourseDTO getRecentCourse(@Param("memberId") Long memberId);

    public List<RecentCourseDTO> getMyCourses(Long memberId);

    List<ApplicantDTO> selectApplicantList(Long planId);

}
