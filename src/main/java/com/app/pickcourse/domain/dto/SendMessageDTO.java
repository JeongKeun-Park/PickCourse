package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import lombok.*;


import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SendMessageDTO {

    @EqualsAndHashCode.Include
    private Long id;  // TBL_MESSAGE.ID
    private Long senderId;   // 보낸 사람
    private String senderEmail;
    private String senderNickname;
    private Long receiverId; // 받은 사람
    private String receiverEmail;
    private String receiverNickname;
    private String content;  // 메시지 내용
    private String sendDate; // 보낸 날짜

    private List<FileVO> attachedFiles;

    public SendMessageVO toVO() {
        SendMessageVO sendMessageVO = new SendMessageVO();
        sendMessageVO.setId(id);
        sendMessageVO.setReceiverId(receiverId);
        sendMessageVO.setSenderId(senderId);
        sendMessageVO.setContent(content);
        sendMessageVO.setSendDate(sendDate);
        return sendMessageVO;
    }
}
