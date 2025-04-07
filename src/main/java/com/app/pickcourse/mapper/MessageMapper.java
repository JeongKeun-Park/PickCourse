package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MessageMapper {

    // 메시지 저장
    void insertMessage(MessageVO messageVO);

    // 메시지 조회
    MessageVO selectMessageById(Long messageId);

    // 메시지 삭제
    void deleteMessageById(Long messageId);

    // 메세지 전체 조회
    List<MessageVO> selectAllMessages();

}