package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.NoticeListDTO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.service.NoticeService;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/main-page")
@RequiredArgsConstructor
@Slf4j
public class MainPageController {

    private final NoticeService noticeService;

    @GetMapping("")
    public String noticePage() {
        return "main-page/notice";
    }

    @GetMapping("/notices")
    @ResponseBody
    public Map<String, Object> getNoticeList(Pagination pagination) {
        Map<String, Object> result = new HashMap<>();

        int total = noticeService.getTotalCount();
        pagination.create(total);

        List<NoticeListDTO> list = noticeService.getList(pagination);

        result.put("noticeList", list);
        result.put("pagination", pagination);

        return result;
    }

    @GetMapping("/notices/{id}")
    @ResponseBody
    public NoticeVO getNotice(@PathVariable("id") Long id) {
        return noticeService.getNotice(id);
    }


}
