package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportListDTO {
    private Long   id;
    private String source;
    private String reportedReason;
    private String createdDate;
    private Long   reportedId;
    private Long   memberId;
    private String memberNickname;
}
