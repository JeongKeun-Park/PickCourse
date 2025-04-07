package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.mapper.RealFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RealFileDAO {
    private final RealFileMapper realFileMapper;

    public void postFeedWrite(Long id, Long feedId) {

        realFileMapper.postFeedWrite(id, feedId);
    }

    public List<FileVO> getRealModify(Long id) {

        return realFileMapper.getRealModify(id);
    }

    public void deleteRealModify(Long id) {
        realFileMapper.deleteRealModify(id);
    }

    public void deleteRealModifyByFeedId(Long feedId) {
        realFileMapper.deleteRealModifyByFeedId(feedId);
    }
}
