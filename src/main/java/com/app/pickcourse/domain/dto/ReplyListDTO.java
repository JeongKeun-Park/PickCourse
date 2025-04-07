package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyListDTO {
    private Long id;
    private String replyContent;
    private String createdDate;
    private Long   feedId;
    private Long   memberId;
    private String memberNickname;
    private String memberFilePath;
    private String memberFileName;
    private String replyAction;  //  신고, 삭제, 등록
}
