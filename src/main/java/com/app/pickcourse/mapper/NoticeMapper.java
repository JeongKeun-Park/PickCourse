package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.NoticeListDTO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {
    // 페이지 처리를 위한 전체 공지사항 건수
    int getCountAll(@Param("search") Search search);

    // 공지사항 등록
    void postNoticeDetail(NoticeVO noticeVO);

    // 공지사항 목록
    List<NoticeListDTO> getNoticeList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    // 공지사항 조회
    Optional<NoticeVO> getNoticeDetail(Long id);

    // 공지사항 수정
    void putNoticeDetail(NoticeVO noticeVO);

    // 공지사항 삭제
    void deleteNoticeDetail(Long id);

    List<NoticeListDTO> selectNoticeList(@Param("pagination") Pagination pagination);

    int selectTotalNoticeCount();

    NoticeVO selectNoticeById(Long id);
}
