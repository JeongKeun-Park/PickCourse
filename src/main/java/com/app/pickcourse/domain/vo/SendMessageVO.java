package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SendMessageVO {

    @EqualsAndHashCode.Include
    private Long id;

    private Long senderId;
    private Long receiverId;

    private String content;

    private String messageDate;
    private String sendDate;

}
