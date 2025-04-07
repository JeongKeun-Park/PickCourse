package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("join")
    public void goToJoinForm(MemberDTO memberDTO){}

    @PostMapping("join")
    public void join(MemberDTO memberDTO){
        memberService.kakaoJoin(memberDTO);
    }


    @GetMapping("join_email")
    public String getJoinEmail(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "join/join_email";
    }

    @PostMapping("join_email")
    public String join(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        if (memberService.getMember(memberDTO.getMemberEmail()).isPresent()) {
            model.addAttribute("errorMessage", "중복된 이메일입니다.");
            return "join/join_email";
        }

        session.setAttribute("signupEmail", memberDTO.getMemberEmail());
        session.setAttribute("signupPassword", memberDTO.getMemberPassword());

        return "redirect:/join/join_check";
    }

    @GetMapping("join_check")
    public String getJoinCheck(HttpSession session, Model model) {
        String email = (String) session.getAttribute("signupEmail");
        String password = (String) session.getAttribute("signupPassword");

        if (email == null || password == null) {
            return "redirect:/join/join_email?error=session_expired";
        }

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(email);
        memberDTO.setMemberPassword(password);

        model.addAttribute("memberDTO", memberDTO);
        return "join/join_check";
    }

    @PostMapping("join_check")
    public String completeSignup(@ModelAttribute MemberDTO memberDTO,
                                 HttpSession session,
                                 Model model) {
        Boolean isVerified = (Boolean) session.getAttribute("isVerified");

        System.out.println("회원가입: isVerified 값 = " + isVerified);

        if (isVerified == null || !isVerified) {
            model.addAttribute("errorMessage", "휴대폰 인증을 완료해야 합니다.");
            return "join/join_check";
        }

        String email = (String) session.getAttribute("signupEmail");
        String password = (String) session.getAttribute("signupPassword");
        String nickname = memberDTO.getMemberNickname();

        // 중복된 이메일 검사
        if (memberService.getMember(email).isPresent()) {
            model.addAttribute("errorMessage", "이미 가입된 이메일입니다.");
            return "join/join_email"; // 중복된 이메일이 있으면 회원가입 진행 X
        }

        // 중복된 닉네임 검사
        if (memberService.getMemberByNickname(nickname).isPresent()) {
            model.addAttribute("errorMessage", "이미 사용 중인 닉네임입니다.");
            return "join/join_check"; // 중복된 닉네임이 있으면 회원가입 진행 X
        }

        // 회원 정보 설정
        memberDTO.setMemberEmail(email);
        memberDTO.setMemberPassword(password);

        // 회원가입 진행
        memberService.join(memberDTO);

        // 로그인 상태 유지
        MemberDTO member = memberService.getMember(memberDTO.getMemberEmail()).get();
        session.setAttribute("memberStatus", "email");
        session.setAttribute("member", member);

        // 세션 정리 (보안)
        session.removeAttribute("signupEmail");
        session.removeAttribute("signupPassword");
        session.removeAttribute("verificationCode");
        session.removeAttribute("isVerified");

        // 회원가입 후 마이페이지로 이동
        return "redirect:/my-page/myPageModify";
    }





}
