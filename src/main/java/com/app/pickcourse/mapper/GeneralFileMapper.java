package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GeneralFileMapper {

    void postFeedWrite(@Param("id") Long fileId, @Param("feedId") Long feedId);

    List<FileVO> getFileList(Long feedId);

    List<FileVO> getFeedModify(Long feedId);

    void deleteFeedModify(Long id);

    void deleteFeedModifyByFeedId(Long feedId);
}
