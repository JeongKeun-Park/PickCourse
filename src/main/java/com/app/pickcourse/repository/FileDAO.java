package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.mapper.FileMapper;
import com.app.pickcourse.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    추가하기
    public void saveMessageFile(FileVO fileVO) {
        fileMapper.insertMessageFile(fileVO);
    }
    public void postFeedWrite(FileVO file) {
        fileMapper.postFeedWrite(file);
    }


    // general, together, real 모두 사용
    public void deleteFeedModify(Long id) {
        fileMapper.deleteFeedModify(id);
    }

    public void deleteFeedModifyByFeedId(Long feedId) {
        fileMapper.deleteModifyFeedByFeedId(feedId);

    }

    public FileVO findLastInsertedFile() {
        return fileMapper.selectLastInsertedFile();

    }
}
