package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SendMessageMapper {

    // 보낸 메시지 저장
    void insertSendMessage(SendMessageVO sendMessageVO);

    // 보낸 메시지 조회
//    SendMessageVO selectSendMessageById(Long messageId);

    // 보낸 메시지 목록 조회
    List<SendMessageDTO> selectSendMessagesBySenderId(Long senderId);

    // 보낸 메시지 삭제
//    void deleteSendMessageById(Long messageId);

    // 보낸 메시지 조회 (페이징 적용)
    List<SendMessageDTO> findBySenderIdWithPagination(
            @Param("senderId") Long senderId,
            @Param("startRow") int startRow,
            @Param("rowCount") int rowCount
    );

    // 보낸 메시지 개수 조회
    int countBySenderId(@Param("senderId") Long senderId);

    public int selectTotalSendMessage(Long sendId);

    public List<SendMessageDTO> selectAllSendMessage(
            @Param("senderId") Long senderId,
            @Param("pagination") Pagination pagination);

    int deleteSentMessageById(@Param("id") Long id);

    SendMessageDTO selectSentMessageById(@Param("id") Long id);

}
