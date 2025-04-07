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
public class TogetherReplyMapperTests {
    @Autowired
    private TogetherReplyMapper mapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void getReplyListTest() {
        List<ReplyListDTO> replyList = mapper.getReplyList(4l);
        replyList.forEach(System.out::println);
    }
}
