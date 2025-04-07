package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class CourseDTO {
    private Long              id;
    private String            courseType;
    private Character         courseIsVolunteer;
    private String            courseName;
    private String            courseDistance;
    private String            courseSchedule;
    private String            courseTheme;
    private String            courseContent;
    private String            courseFilePath;
    private String            courseFileSize;
    private String            courseFileName;
    private Long              adminId;
    private String            adminName;
    private String            createdDate;
    private String            updatedDate;
    private Long              volunteerId;
    private String            volunteerStartDate;
    private String            volunteerEndDate;
    private String            volunteerDeadline;
    private int               volunteerMaxPersonnel;
    private int               volunteerMinPersonnel;
    private int               volunteerParticipants;    // 총 참여자 수
    List<PathVO>              paths;
    List<String>              excludeContents;
    List<String>              includeContents;
    List<String>              prepareContents;
    List<String>              scheduleContents;

    public CourseVO toCourseVO() {
        CourseVO courseVO = new CourseVO();
        courseVO.setId(id);
        courseVO.setCourseType(courseType);
        courseVO.setCourseIsVolunteer(courseIsVolunteer);
        courseVO.setCourseName(courseName);
        courseVO.setCourseDistance(courseDistance);
        courseVO.setCourseSchedule(courseSchedule);
        courseVO.setCourseTheme(courseTheme);
        courseVO.setCourseContent(courseContent);
        courseVO.setCourseFilePath(courseFilePath);
        courseVO.setCourseFileSize(courseFileSize);
        courseVO.setCourseFileName(courseFileName);
        courseVO.setAdminId(adminId);
        return courseVO;
    }

    public VolunteerVO toVolunteerVO() {
        VolunteerVO volunteerVO = new VolunteerVO();
        volunteerVO.setId(id);
        volunteerVO.setVolunteerStartDate(volunteerStartDate);
        volunteerVO.setVolunteerEndDate(volunteerEndDate);
        volunteerVO.setVolunteerDeadline(volunteerDeadline);
        volunteerVO.setVolunteerMaxPersonnel(volunteerMaxPersonnel);
        volunteerVO.setVolunteerMinPersonnel(volunteerMinPersonnel);
        return volunteerVO;
    }
}
