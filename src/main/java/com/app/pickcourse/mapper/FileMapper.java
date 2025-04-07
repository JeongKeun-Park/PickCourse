package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.SendMessageFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    void postFeedWrite(FileVO fileVO);

    // 슈퍼키만 삭제하면 자식들도 삭제

    void deleteFeedModify(Long id);

    void deleteModifyFeedByFeedId(Long feedId);

    void deleteModifyFeed(Long feedId);

//    메세지 파일
    public void insertMessageFile(FileVO fileVO);

    void insertFile(FileVO fileVO);

    // 최근 추가된 파일 조회
    FileVO selectLastInsertedFile();

}
