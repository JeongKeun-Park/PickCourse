package com.app.pickcourse.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class ReplyReportVO {
    private Long   id;
    private String reportedReason;
    private Long   reportedId;
    private Long   memberId;
    private String createdDate;
    private String updatedDate;
}
