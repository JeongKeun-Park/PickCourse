package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.NoticeListDTO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.repository.NoticeDAO;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class NoticeService {

    private final NoticeDAO noticeDAO;

    public Map<String, Object> getNoticeList(int page) {
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        int total = noticeDAO.getTotalCount();
        pagination.create(total);

        List<NoticeListDTO> list = noticeDAO.getList(pagination);

        Map<String, Object> map = new HashMap<>();
        map.put("noticeList", list);
        map.put("pagination", pagination);
        return map;
    }

    public NoticeVO getNotice(Long id) {
        return noticeDAO.getDetail(id);
    }

    public int getTotalCount() {
        return noticeDAO.getTotalCount();
    }

    public List<NoticeListDTO> getList(Pagination pagination) {
        return noticeDAO.getList(pagination);
    }


}
