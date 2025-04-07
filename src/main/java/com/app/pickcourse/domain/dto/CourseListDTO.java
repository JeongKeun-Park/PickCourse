package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.CourseVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class CourseListDTO {
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
    private String    adminName;
    private String    createdDate;
    private String    updatedDate;

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

}
