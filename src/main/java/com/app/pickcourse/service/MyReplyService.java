package com.app.pickcourse.service;

import com.app.pickcourse.repository.MyFeedDAO;
import com.app.pickcourse.repository.MyReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyReplyService {
    private final MyReplyDAO myReplyDAO;

    public int getMyReplyCount(Long memberId) {
        return myReplyDAO.getMyReplyCount(memberId);
    }


}
