package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceiveMessageMapper {

    // 받은 메시지 저장
    void insertReceiveMessage(ReceiveMessageVO receiveMessageVO);

    // 받은 메시지 조회
    List<ReceiveMessageDTO> selectReceiveMessagesByReceiverId(Long receiverId);

    // 받은 메시지 개수 조회 (페이징을 위해 추가)
    int countByReceiverId(Long receiverId);

    // 페이징을 적용한 받은 메시지 조회
    List<ReceiveMessageDTO> findByReceiverIdWithPagination(
            @Param("receiverId") Long receiverId,
            @Param("pagination") Pagination pagination);

    public int selectTotalReceiveMessage(Long receiverId);

    public List<ReceiveMessageDTO> selectAllReceiveMessage(
            @Param("receiverId") Long receiverId,
            @Param("pagination") Pagination pagination);

    public ReceiveMessageDTO selectMessageById(@Param("id") Long id);

    public int deleteReceiveMessageById(@Param("id") Long id);

//    public String updateToChecked(Long id);

    public int updateToChecked(Long id);

}
