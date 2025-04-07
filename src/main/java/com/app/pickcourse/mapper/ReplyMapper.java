package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.PaginationOnePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReplyMapper {

    int getCountAll(Long feedId);

    void postReplyList(ReplyVO replyVO);

    Optional<ReplyDetailDTO> getReportDetail(Long id);

    List<ReplyListDTO> getReplyList(@Param("feedId") Long feedId, @Param("pagination") PaginationOnePage pagination);

    void deleteReplyList(Long id);

    int getMyCountAll(long memberId);

    List<ReplyListDTO> getMyReplyList(@Param("memberId") long memberId, @Param("pagination") PaginationOnePage pagination);


}
