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
public class RecruitPlanDTO {

    private Long planId;
    private String createdDate;
    private String planName;
    private String planCreatedDate;
    private int participantCount;
    private int maxPersonnel;
    private String filePath;
    private String fileName;

}
