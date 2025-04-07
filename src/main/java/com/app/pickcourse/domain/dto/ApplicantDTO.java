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
public class ApplicantDTO {

    private String createdDate;
    private String courseFilePath;
    private String courseFileName;
    private String planName;
    private String memberEmail;
    private String memberNickname;
    private int participantCount;

}
