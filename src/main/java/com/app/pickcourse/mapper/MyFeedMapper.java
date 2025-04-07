package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MyFeedMapper {

    public int getMyFeedCount(@Param("memberId") Long memberId);

    public int getMyReviewCount(@Param("memberId") Long memberId);

    List<Map<String, Object>> getRecentFeeds(@Param("memberId") Long memberId);

}
