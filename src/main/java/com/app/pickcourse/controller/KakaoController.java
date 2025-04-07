package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.KakaoService;
import com.app.pickcourse.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {

    private final HttpSession session;
    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/kakao/login")
    public String login(String code) {
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberDTO> kakaoInfo = kakaoService.getKakaoInfo(token);

        log.info("kakaoInfo: {}", kakaoInfo);

        MemberDTO memberDTO = kakaoInfo.orElseThrow(() -> new RuntimeException("카카오 사용자 정보 조회 실패"));

        Optional<MemberDTO> foundMember = memberService.getMember(memberDTO.getMemberEmail());

        if (!foundMember.isPresent()) {
            memberService.kakaoJoin(memberDTO);
            foundMember = memberService.getMember(memberDTO.getMemberEmail());
        }

        session.setAttribute("memberStatus", "kakao");
        session.setAttribute("member", foundMember.get());

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null) {
            session.removeAttribute("redirectAfterLogin");
            return "redirect:" + redirectUrl;
        }

        return "redirect:/";
    }
}
