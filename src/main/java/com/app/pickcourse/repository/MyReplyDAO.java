package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.MyFeedMapper;
import com.app.pickcourse.mapper.MyReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyReplyDAO {
    private final MyReplyMapper myFeedMapper;

    public int getMyReplyCount(Long memberId){
        return myFeedMapper.getMyReplyCount(memberId);
    }
}
