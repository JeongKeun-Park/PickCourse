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
public class MyPayListDTO {

    private String payDate;
    private String courseFilePath;
    private String courseFileName;
    private String planName;
    private int payPrice;
    private String courseType;
    private int participantNumber;
    private Long planId;

}
