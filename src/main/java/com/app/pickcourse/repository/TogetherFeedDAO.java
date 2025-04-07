package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.TogetherFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TogetherFeedDAO {
    private final TogetherFeedMapper togetherFeedMapper;

    public void postFeedWrite(Long memberId, FeedVO feedVO) {
        togetherFeedMapper.postFeedWrite(feedVO.getId(), memberId);
    }

    public FeedDTO getFeedModify(Long id) {
        return togetherFeedMapper.getFeedModify(id).orElseThrow(() -> new RuntimeException("feedDTO not found"));
    }

    public void deleteFeedModify(Long id) {
        togetherFeedMapper.deleteFeedModify(id);
    }

    public List<FeedListDTO> getFeedList() {
        return togetherFeedMapper.getFeedList();
    }

    public List<FeedListDTO> getMyFeedList(Long memberId) {
        return togetherFeedMapper.getMyFeedList(memberId);
    }
}
