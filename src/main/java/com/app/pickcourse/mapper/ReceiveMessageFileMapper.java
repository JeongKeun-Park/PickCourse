package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageFileDTO;
import com.app.pickcourse.domain.dto.SendMessageFileDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveMessageFileMapper {

    public void insert(ReceiveMessageFileVO receiveMessageFileVO);

    //  파일 조회
    public ReceiveMessageFileDTO selectByReceiveMessageId(Long receiveMessageId);

    // 파일삭제
    public void deleteByReceiveMessageId(Long receiveMessageId);

}
