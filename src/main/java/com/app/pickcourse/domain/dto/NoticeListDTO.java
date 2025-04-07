package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoticeListDTO {
    private Long id;
    private String noticeTitle;
    private Long   adminId;
    private String adminName;
    private String createdDate;
}
