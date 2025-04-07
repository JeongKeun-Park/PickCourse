package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.ReceivePaginationDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.dto.SendPaginationDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.MessageVO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.MessageDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import com.app.pickcourse.util.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MessageServiceTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SendMessageDAO sendMessageDAO;

    @Autowired
    private ReceiveMessageDAO receiveMessageDAO;

    @Autowired
    private MessageDAO messageDAO;

    private SendMessageDTO sendMessageDTO;
    private ReceiveMessageDTO receiveMessageDTO;
    @Autowired
    private MemberDAO memberDAO;

//    @Test
//    public void testSendMessage() {
//
//        SendMessageDTO sendMessageDTO = new SendMessageDTO();
//        sendMessageDTO.setSenderId(1L);
//        sendMessageDTO.setSenderEmail("test@test.com");
//        sendMessageDTO.setSenderNickname("Test");
//        sendMessageDTO.setReceiverId(2L);
//        sendMessageDTO.setReceiverEmail("dao@test.com");
//        sendMessageDTO.setReceiverNickname("다오수정테스트");
//        sendMessageDTO.setContent("서비스 테스트 메시지입니다.123");
//
//        messageService.sendMessage(sendMessageDTO, file);
//
//        List<SendMessageDTO> sentMessages = sendMessageDAO.findBySenderId(sendMessageDTO.getSenderId());
//        log.info("보낸 메시지 조회 성공: {}", sentMessages);
//
//        List<ReceiveMessageDTO> receivedMessages = receiveMessageDAO.findByReceiverId(sendMessageDTO.getReceiverId());
//        log.info("받은 메시지 조회 성공: {}", receivedMessages);
//
//        MessageVO message = messageDAO.findById(sentMessages.get(0).getId());
//        log.info("슈퍼키 메시지 조회 성공: {}", message);
//    }

    @Test
    public void selectReceiveMessageByReceiverId() {
        List<ReceiveMessageDTO> receiveMessages = receiveMessageDAO.findByReceiverId(2L);

        if (!receiveMessages.isEmpty()) {
            log.info("총 {}개의 받은 메시지 조회 성공!", receiveMessages.size());
            for (ReceiveMessageDTO message : receiveMessages) {
                log.info("메시지 ID: {}, 보낸 사람 ID: {}, 내용: {}",
                        message.getId(), message.getSenderId(), message.getContent());
            }
        } else {
            log.info("받은 메시지가 없습니다.");
        }
    }

    @Test
    public void selectSendMessageBySenderId() {
        List<SendMessageDTO> sendMessages = sendMessageDAO.findBySenderId(1L);

        if (!sendMessages.isEmpty()) {
            log.info("총 {}개의 보낸 메시지 조회 성공!", sendMessages.size());
            for (SendMessageDTO message : sendMessages) {
                log.info("메시지 ID: {}, 받은 사람 ID: {}, 내용: {}",
                        message.getId(), message.getReceiverId(), message.getContent());
            }
        } else {
            log.info("보낸 메시지가 없습니다.");
        }
    }

//    보낸메세지 삭제
    @Test
    public void deleteSendMessageById() {
        Long id = 21L;

        messageService.deleteSendMessageById(id);

        List<SendMessageDTO> leftMessages = sendMessageDAO.findBySenderId(1L);
        log.info("남은 보낸 메시지: {}", leftMessages);
    }

//    받은메세지 삭제
    @Test
    public void deleteReceiveMessageById() {
        Long id = 21L;
        messageService.deleteReceiveMessageById(id);

        List<ReceiveMessageDTO> leftMessages = receiveMessageDAO.findByReceiverId(1L);
        log.info("남은 받은 메세지: {}", leftMessages);

    }

//    슈퍼키 메세지 삭제
    @Test
    public void deleteMessageById() {
        Long id = 21L;
        messageService.deleteMessageById(id);

        List<MessageVO> leftMessages = messageDAO.findAll();
        log.info("남은 메세지: {}", leftMessages);
    }


//    @Test
//    public void testSendMessageByEmail() {
//        Optional<Long> receiverId = memberDAO.findIdByEmail("test@test.com");
//        Optional<Long> senderId = memberDAO.findIdByEmail("dao@test.com");
//
//        if (receiverId.isEmpty()) {
//            throw new RuntimeException("받는 사람 이메일을 찾을 수 없습니다.");
//        }
//
//        SendMessageDTO sendMessageDTO = new SendMessageDTO();
//        sendMessageDTO.setSenderId(senderId.get());
//        sendMessageDTO.setReceiverId(receiverId.get());
//        sendMessageDTO.setContent("이메일로 메시지 보내기 테스트");
//
//        messageService.sendMessage(sendMessageDTO);
//
//        log.info("메시지 전송 성공: {}", sendMessageDTO);
//    }

    @Test
    public void testReceiveMessageWithPagination() {
        Pagination pagination = new Pagination();
        ReceivePaginationDTO receivePaginationDTO = new ReceivePaginationDTO();

        pagination.create(messageService.getTotalReceiveMessage(2L));
        receivePaginationDTO.setPagination(pagination);
        receivePaginationDTO = messageService.getReceiveList(2L, pagination);

        receivePaginationDTO.getReceiveMessages().forEach((message) -> {log.info(message.toString());});
        log.info(receivePaginationDTO.toString());

    }

    @Test
    public void testSendMessageWithPagination() {
        Pagination pagination = new Pagination();
        SendPaginationDTO sendPaginationDTO = new SendPaginationDTO();

        pagination.create(messageService.getTotalSendMessage(2L));
        sendPaginationDTO.setPagination(pagination);
        sendPaginationDTO = messageService.getSendList(2L, pagination);

        sendPaginationDTO.getSendMessages().forEach((message) -> {log.info(message.toString());});
        log.info(sendPaginationDTO.toString());

    }
}






