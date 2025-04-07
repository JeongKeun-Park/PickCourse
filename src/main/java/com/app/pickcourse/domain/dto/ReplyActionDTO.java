package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyActionDTO {
    private Long id;
    private String replyContent;
    private String replyAction;  //  신고, 삭제, 등록
}
