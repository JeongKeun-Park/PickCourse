package com.app.pickcourse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyReplyMapper {

    public int getMyReplyCount(@Param("memberId") Long memberId);



}
