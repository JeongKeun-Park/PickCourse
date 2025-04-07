package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseVO {
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
}
