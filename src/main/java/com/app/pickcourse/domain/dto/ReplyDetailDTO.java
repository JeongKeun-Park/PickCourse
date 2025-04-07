package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyDetailDTO {
    private Long   id;
    private String source;
    private String content;
    private String memberNickname;
    private Long   feedId;
    private Long   memberId;
    private String createdDate;
}
