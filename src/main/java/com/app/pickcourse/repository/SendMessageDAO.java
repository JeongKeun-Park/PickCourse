package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.mapper.SendMessageMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SendMessageDAO {
    private final SendMessageMapper sendMessageMapper;

    // 보낸 메시지 저장
    public void save(SendMessageVO sendMessageVO) {
        sendMessageMapper.insertSendMessage(sendMessageVO);
    }

    // 보낸 메시지 조회 (보낸 사람 기준)
    public List<SendMessageDTO> findBySenderId(Long senderId) {
        return sendMessageMapper.selectSendMessagesBySenderId(senderId);
    }

    // 보낸 메시지 삭제
    public void delete(Long id) {
        sendMessageMapper.deleteSentMessageById(id);
    }


    // 보낸 메시지 조회
    public List<SendMessageDTO> findBySenderIdWithPagination(Long senderId, int startRow, int rowCount) {
        return sendMessageMapper.findBySenderIdWithPagination(senderId, startRow, rowCount);
    }

    // 보낸 메시지 개수 조회
    public int countBySenderId(Long senderId) {
        return sendMessageMapper.countBySenderId(senderId);
    }

    // 전체 개수
    public int findTotalSendMessage(Long id) { return sendMessageMapper.selectTotalSendMessage(id); }

    // 전체 조회
    public List<SendMessageDTO> findAllSendMessage(Long senderId, Pagination pagination) {
        return sendMessageMapper.selectAllSendMessage(senderId, pagination);
    }

    public SendMessageDTO findMessageById(Long id) {
        return sendMessageMapper.selectSentMessageById(id);
    }

    public int deleteSendMessage(Long id) {
        return sendMessageMapper.deleteSentMessageById(id);
    }



}
