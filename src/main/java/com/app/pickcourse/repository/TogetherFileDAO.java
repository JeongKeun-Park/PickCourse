package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.mapper.TogetherFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TogetherFileDAO {
    private final TogetherFileMapper togetherFileMapper;

    public void postFeedWrite(Long id, Long feedId) {
        togetherFileMapper.postFeedWrite(id, feedId);
    }

    public List<FileVO> getFeedModify(Long id) {
        return togetherFileMapper.getFeedModify(id);
    }

    public void deleteFeedModify(Long id) {
        togetherFileMapper.deleteFeedModify(id);
    }

    public void deleteFeedModifyByFeedId(Long feedId) {
        togetherFileMapper.deleteFeedModify(feedId);
    }
}
