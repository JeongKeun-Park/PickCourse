package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.mapper.GeneralReplyMapper;
import com.app.pickcourse.mapper.TogetherReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TogetherReplyDAO {
    private final TogetherReplyMapper togetherReplyMapper;

    public void deleteReplyList(Long id) {
        togetherReplyMapper.deleteReplyList(id);
    }

    public void postReplyList(ReplyVO replyVO) {
        togetherReplyMapper.postReplyList(replyVO);
    }
}
