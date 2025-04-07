package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.mapper.ReceiveMessageMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiveMessageDAO {
    private final ReceiveMessageMapper receiveMessageMapper;

    // 받은 메시지 저장
    public void save(ReceiveMessageVO receiveMessageVO) {
        receiveMessageMapper.insertReceiveMessage(receiveMessageVO);
    }

    // 받은 메시지 조회
    public List<ReceiveMessageDTO> findByReceiverId(Long receiverId) {
        return receiveMessageMapper.selectReceiveMessagesByReceiverId(receiverId);
    }

    // 받은 메시지 삭제
    public void delete(Long id) {
        receiveMessageMapper.deleteReceiveMessageById(id);
    }

    // 받은 메시지 개수 조회 (페이징을 위해 추가)
    public int countByReceiverId(Long receiverId) {
        return receiveMessageMapper.countByReceiverId(receiverId);
    }

    // 페이징된 받은 메시지 조회
    public List<ReceiveMessageDTO> findByReceiverIdWithPagination(Long receiverId, Pagination pagination) {
        return receiveMessageMapper.findByReceiverIdWithPagination(receiverId, pagination);
    }
    // 전체 개수
    public int findTotalReceiveMessage(Long id) { return receiveMessageMapper.selectTotalReceiveMessage(id); }

    // 전체 조회
    public List<ReceiveMessageDTO> findAllReceiveMessage(Long receiverId, Pagination pagination) {
        return receiveMessageMapper.selectAllReceiveMessage(receiverId, pagination);
    }

    public ReceiveMessageDTO findMessageById(Long id) {
        return receiveMessageMapper.selectMessageById(id);
    }

    public int deleteReceiveMessage(Long id) {
        return receiveMessageMapper.deleteReceiveMessageById(id);
    }

//    public String updateToChecked(Long id) {
//        return receiveMessageMapper.updateToChecked(id);
//    }

    public int updateToChecked(Long id) {
        return receiveMessageMapper.updateToChecked(id);
    }
}
