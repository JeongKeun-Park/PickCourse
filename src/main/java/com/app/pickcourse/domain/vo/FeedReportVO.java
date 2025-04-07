package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
public class FeedReportVO {
    private Long   id;
    private String reportedReason;
    private Long   reportedId;
    private Long   memberId;
    private String createdDate;
    private String updatedDate;
}
