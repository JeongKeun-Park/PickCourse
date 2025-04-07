package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.SendMessageFileDTO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.SendMessageFileVO;
import com.app.pickcourse.mapper.SendMessageFileMapper;
import com.app.pickcourse.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SendMessageFileDAO {
    private final SendMessageFileMapper sendMessageFileMapper;

//    추가하기
    public void saveMessageFile(SendMessageFileVO sendMessageFileVO) {
        sendMessageFileMapper.insert(sendMessageFileVO);
    }

//    파일 조회
    public SendMessageFileDTO selectBySendMessageId(Long sendMessageId) {
        return sendMessageFileMapper.selectBySendMessageId(sendMessageId);
    }

//    파일 삭제
    public void deleteBySendMessageId(Long sendMessageId){
        sendMessageFileMapper.deleteBySendMessageId(sendMessageId);
    }
}
