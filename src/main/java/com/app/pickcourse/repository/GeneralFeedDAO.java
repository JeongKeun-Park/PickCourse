package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneralFeedDAO {
    private final GeneralFeedMapper generalFeedMapper;


    public void postFeedWrite(Long memberId, FeedVO feedVO) {
        generalFeedMapper.postFeedWrite(feedVO.getId(), memberId);
    }

    public FeedDTO getFeedModify(Long id) {
        return generalFeedMapper.getFeedModify(id).orElseThrow(() -> new RuntimeException("feedDTO not found"));
    }

    public void deleteFeedModify(Long id) {
        generalFeedMapper.deleteFeedModify(id);
    }
}
