package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.domain.vo.PathVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseSelectDTO {
    @EqualsAndHashCode.Include
    private Long      id;
    private String    courseType;
    private Character courseIsVolunteer;
    private String    courseName;
    private String    courseDistance;
    private String    courseSchedule;
    private String    courseTheme;
    private String    courseContent;
    private String    courseFilePath;
    private String    courseFileSize;
    private String    courseFileName;
    private Long      adminId;
    private String    createdDate;
    private String    updatedDate;
    private List<PathVO> paths;
    private List<PlanDTO> plans;
    private String memberName;
    private List<ParticipantDTO> participants;

    public CourseVO toVO() {
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
        courseVO.setCreatedDate(createdDate);
        courseVO.setUpdatedDate(updatedDate);
        return courseVO;
    }
}
