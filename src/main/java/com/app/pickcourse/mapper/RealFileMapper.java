package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealFileMapper {

    void postFeedWrite(@Param("id") Long fileId, @Param("feedId") Long feedId);

    List<FileVO> getRealModify(Long feedId);

    void deleteRealModify(Long id);

    void deleteRealModifyByFeedId(Long feedId);
}
