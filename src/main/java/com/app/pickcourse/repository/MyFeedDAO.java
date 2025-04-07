package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.MyFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MyFeedDAO {
    private final MyFeedMapper myFeedMapper;

    public int getMyFeedCount(Long memberId){
        return myFeedMapper.getMyFeedCount(memberId);
    }

    public int getMyReviewCount(Long memberId){
        return myFeedMapper.getMyReviewCount(memberId);
    }

    public List<Map<String, Object>> getRecentFeeds(Long memberId) {
        return myFeedMapper.getRecentFeeds(memberId);
    }

}
