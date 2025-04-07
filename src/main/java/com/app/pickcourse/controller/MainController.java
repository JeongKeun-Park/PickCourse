package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MainDTO;
import com.app.pickcourse.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String mainPage(Model model){
        MainDTO mainDTO = mainService.getCourses();
//        HTML에서 Timeleaf와 같은 템플릿엔진에서 mainDTO에 담긴 데이터를 표시하거나 사용 할 수 있도록 하기위해
//        model객체에 담아서 전달한다.
        model.addAttribute("mainDTO", mainDTO);
        return "index";
    }
}
