package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.mapper.GeneralReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneralReplyDAO {
    private final GeneralReplyMapper generalReplyMapper;

    public void deleteReplyList(Long id) {
        generalReplyMapper.deleteReplyList(id);
    }

    public void postReplyList(ReplyVO replyVO) {
        generalReplyMapper.postReplyList(replyVO);
    }
}
