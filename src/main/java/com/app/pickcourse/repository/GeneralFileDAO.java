package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.mapper.FileMapper;
import com.app.pickcourse.mapper.GeneralFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GeneralFileDAO {
    private final GeneralFileMapper generalFileMapper;

    public void postFeedWrite(Long id, Long feedId) {
        generalFileMapper.postFeedWrite(id, feedId);
    }

    public List<FileVO> getFeedModify(Long feedId) {
        return generalFileMapper.getFeedModify(feedId);
    }

    public void deleteFeedModify(Long id) {
        generalFileMapper.deleteFeedModify(id);
    }

    public void deleteFeedModifyByFeedId(Long feedId) {
        generalFileMapper.deleteFeedModifyByFeedId(feedId);
    }
}
