package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class GeneralReplyMapperTests {
    @Autowired
    private GeneralReplyMapper mapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void postReplyListTest() {
        ReplyVO replyListDTO = new ReplyVO();
        replyListDTO.setReplyContent("아름다운 밤이에요test");
        replyMapper.postReplyList(replyListDTO);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(1l);
        FeedVO feedVO = new FeedVO();
        feedVO.setId(2l);
        mapper.postReplyList(replyListDTO);
    }

    @Test
    public void getReplyListTest() {
        List<ReplyListDTO> replyList = mapper.getReplyList(2l);
        replyList.forEach(System.out::println);
    }
}
