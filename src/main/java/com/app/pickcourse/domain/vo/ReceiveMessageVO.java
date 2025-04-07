package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageVO {

    @EqualsAndHashCode.Include
    private Long id;

    private Long receiverId;
    private Long senderId;

    private String content;

    private String messageDate;
    private String receiveDate;

}
