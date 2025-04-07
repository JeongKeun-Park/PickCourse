package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.NoticeListDTO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.mapper.NoticeMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    public int getCountAll(Search search) {
        return noticeMapper.getCountAll(search);
    }

    public List<NoticeListDTO> getNoticeList(Pagination pagination, Search search) {
        return noticeMapper.getNoticeList(pagination, search);
    }

    public NoticeVO getNoticeDetail(Long id) {
        return noticeMapper.getNoticeDetail(id).orElseThrow(() -> new RuntimeException("notice not found !!!!"));
    }

    public void putNoticeDetail(NoticeVO notice) {
        noticeMapper.putNoticeDetail(notice);
    }

    public void deleteNoticeDetail(Long id) {
        noticeMapper.deleteNoticeDetail(id);
    }

    public void postNoticeDetail(NoticeVO notice) {
        noticeMapper.postNoticeDetail(notice);
    }

    public List<NoticeListDTO> getList(Pagination pagination) {
        return noticeMapper.selectNoticeList(pagination);
    }

    public int getTotalCount() {
        return noticeMapper.selectTotalNoticeCount();
    }

    public NoticeVO getDetail(Long id) {
        return noticeMapper.selectNoticeById(id);
    }
}
