package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.MessageVO;
import com.app.pickcourse.util.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ReceiveMessageMapper receiveMapper;

    @Autowired
    private SendMessageMapper sendMapper;



    @Test
    public void testSendMessage() {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setSenderId(3L);
        sendMessageDTO.setSenderEmail("test@test.com");
        sendMessageDTO.setSenderNickname("test");
        sendMessageDTO.setReceiverId(1L);
        sendMessageDTO.setReceiverEmail("2ndtest@2ndtest.com");
        sendMessageDTO.setReceiverNickname("홍길동");
        sendMessageDTO.setContent("테스트 대성공");

        // 먼저 메시지를 저장하고 ID를 가져옴
        MessageVO messageVO = new MessageVO();
        messageVO.setContent(sendMessageDTO.getContent());
        messageMapper.insertMessage(messageVO); //슈퍼키 테이블 저장
        log.info(messageVO.toString());

        // 메시지 ID 설정
        sendMessageDTO.setId(messageVO.getId());

        // 보낸 메시지 테이블에 저장
        sendMapper.insertSendMessage(sendMessageDTO.toVO());

        // 받은 메시지 처리
        ReceiveMessageDTO receiveMessageDTO = new ReceiveMessageDTO();
        receiveMessageDTO.setId(sendMessageDTO.getId());
        receiveMessageDTO.setSenderId(sendMessageDTO.getSenderId());
        receiveMessageDTO.setSenderEmail(sendMessageDTO.getSenderEmail());
        receiveMessageDTO.setSenderNickname(sendMessageDTO.getSenderNickname());
        receiveMessageDTO.setReceiverId(sendMessageDTO.getReceiverId());
        receiveMessageDTO.setReceiverEmail(sendMessageDTO.getReceiverEmail());
        receiveMessageDTO.setReceiverNickname(sendMessageDTO.getReceiverNickname());
        receiveMessageDTO.setContent(sendMessageDTO.getContent());

        receiveMapper.insertReceiveMessage(receiveMessageDTO.toVO());
    }

    @Test
    public void testSelectReceivedMessages() {
        Long receiverId = 2L; // 받은 사람 ID (테스트 계정)

        // 받은 쪽지 조회
        List<ReceiveMessageDTO> receivedMessages = receiveMapper.selectAllReceiveMessage(receiverId, new Pagination());

        // 로그 출력
        if (!receivedMessages.isEmpty()) {
            log.info("받은 쪽지 조회 성공! 총 {}개의 메시지", receivedMessages.size());
            for (ReceiveMessageDTO message : receivedMessages) {
                log.info("메시지 ID: {}, 보낸 사람: {}, 내용: {}",
                        message.getId(), message.getSenderNickname(), message.getContent());
            }
        } else {
            log.info("받은 쪽지가 없습니다.");
        }
    }

    @Test
    public void testDeleteReceiveMessageById() {
        Long id = 3L;

        receiveMapper.deleteReceiveMessageById(id);
    }

    @Test
    public void testSelectReceiveMessages() {
        Pagination pagination = new Pagination();
        Long receiverId = 2L;
        pagination.create(5);
        List<ReceiveMessageDTO> receivedMessages = receiveMapper.selectAllReceiveMessage(receiverId, pagination);
        log.info(receivedMessages.toString());
    }

}
