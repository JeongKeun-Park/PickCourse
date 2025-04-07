package com.app.pickcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/introduce/*")
@RequiredArgsConstructor
@Slf4j
public class IntroduceController {
    @GetMapping("/introduce-main")
    public void getIntroduce() {}
}
