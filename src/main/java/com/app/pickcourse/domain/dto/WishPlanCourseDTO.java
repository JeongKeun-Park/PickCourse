package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishPlanCourseDTO {
    private Long memberId;
    private Long planId;
    private String planName;
    private String courseFilePath;
    private String courseFileName;
}
