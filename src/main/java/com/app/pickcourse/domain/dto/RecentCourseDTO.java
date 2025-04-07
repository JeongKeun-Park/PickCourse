package com.app.pickcourse.domain.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecentCourseDTO {
    private Long courseId;
    private Long planId;
    private String courseType;
    private String courseName;
    private String courseFilePath;
    private String courseFileName;
}
