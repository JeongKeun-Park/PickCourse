package com.app.pickcourse.service;

import com.app.pickcourse.repository.MyFeedDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyFeedService {
    private final MyFeedDAO myFeedDAO;

    public int getMyFeedCount(Long memberId) {
        return myFeedDAO.getMyFeedCount(memberId);
    }

    public int getMyReviewCount(Long memberId) {
        return myFeedDAO.getMyReviewCount(memberId);
    }

    public List<Map<String, Object>> getRecentFeeds(Long memberId) {
        return myFeedDAO.getRecentFeeds(memberId);
    }

}
