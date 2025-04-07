package com.app.pickcourse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RealStarMapper {

    void postFeedWrite(@Param("feedId") Long feedId, @Param("realStar") int realStar);

    void postReviewModify(@Param("realStar") int realStar, @Param("feedId") Long feedId);
}
