package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {

    void postFeedWrite(@Param("tagContent")  String tagContent, @Param("feedId") Long feedId);

    List<TagVO> getFeedList(Long feedId);

    // 태그 전체 삭제 :: 후 전체 삽입 => void postFeedWrite(TagVO tagVO)
    void deleteFeedModify(Long feedId);

    List<String> getFeedModify(Long feedId);

}
