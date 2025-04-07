package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.SendMessageFileDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageFileVO;
import com.app.pickcourse.domain.vo.SendMessageFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SendMessageFileMapper {
//  파일 추가
    public void insert(SendMessageFileVO sendMessageFileVO);

//  파일 조회
    public SendMessageFileDTO selectBySendMessageId(Long sendMessageId);

// 파일 삭제
    public void deleteBySendMessageId(Long sendMessageId);
}
