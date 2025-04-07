package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MessageVO {

    @EqualsAndHashCode.Include
    private Long id;  // TBL_MESSAGE의 ID

    private String content;  // 메시지 내용

    private String createdDate;  // 메시지 생성 날짜
}
