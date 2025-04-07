package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.TagVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final TagMapper tagMapper;

    public void postFeedWrite(String tagContent, Long feedId) {
        tagMapper.postFeedWrite(tagContent, feedId);
    }

    public List<String> getFeedModify(Long feedId) {
        return  tagMapper.getFeedModify(feedId);
    }

    public void deleteFeedModify(Long feedId) {
        tagMapper.deleteFeedModify(feedId);
    }
}
