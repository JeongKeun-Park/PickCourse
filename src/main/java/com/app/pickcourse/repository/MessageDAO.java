package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.MessageVO;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MessageDAO {
    private final MessageMapper messageMapper;


    // 메시지 저장 (슈퍼키)
    public void save(MessageVO messageVO) {
        messageMapper.insertMessage(messageVO);
    }

    // 메시지 조회
    public MessageVO findById(Long id) {
        return messageMapper.selectMessageById(id);
    }

    // 메시지 삭제
    public void delete(Long id) {
        messageMapper.deleteMessageById(id);
    }

    // 메세지 전체 조회
    public List<MessageVO> findAll(){
        return messageMapper.selectAllMessages();
    }


}
