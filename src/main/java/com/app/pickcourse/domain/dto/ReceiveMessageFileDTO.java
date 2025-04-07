package com.app.pickcourse.domain.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private Long receiveMessageId;
    private String createDate;
    private String updateDate;
}