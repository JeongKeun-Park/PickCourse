package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TourListDTO {
    private Long                id;
    private String              planName;
    private String              planFilePath;
    private String              planFileName;
    private String              planStartDate;
    private String              planEndDate;
    private int                 planPrice;
    private int                 planMaxPersonnel;
    private int                 participants;
    private Long                memberId;
    private String              memberNickname;

}
