// 2025.02.24 조승찬
package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.service.FeedsService;
import com.app.pickcourse.service.MyFeedService;
import com.app.pickcourse.util.PaginationOnePage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Slf4j
public class MyFeedsController {
    private final MyFeedService myFeedService;



}
