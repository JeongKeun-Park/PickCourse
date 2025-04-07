package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageDTO {

    @EqualsAndHashCode.Include
    private Long id;  // TBL_MESSAGE.ID
    private Long receiverId; // 받은 사람
    private String receiverEmail;
    private String receiverNickname;
    private Long senderId;   // 보낸 사람
    private String senderEmail;
    private String senderNickname;
    private String content;  // 메시지 내용
    private String receiveDate; // 받은 날짜
    private String receiveMessageCheck; // 읽음 여부
    private List<FileVO> attachedFiles;

    public ReceiveMessageVO toVO() {
        ReceiveMessageVO receiveMessageVO = new ReceiveMessageVO();
        receiveMessageVO.setId(id);
        receiveMessageVO.setSenderId(senderId);
        receiveMessageVO.setReceiverId(receiverId);
        receiveMessageVO.setContent(content);
        receiveMessageVO.setReceiveDate(receiveDate);
        return receiveMessageVO;
    }
}
