package com.app.pickcourse.controller;

import com.app.pickcourse.service.SMSService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AuthController (인증 관련 API 컨트롤러)
 *
 * - 인증번호 발송 및 검증 기능 제공
 * - 휴대폰 번호로 인증번호를 전송하고, 이를 검증하는 역할
 * - 세션(HttpSession)을 활용하여 인증 상태를 관리
 */
@RestController // 이 클래스가 REST API를 제공하는 컨트롤러임을 나타냄
@RequestMapping("/auth") // 모든 API의 기본 경로를 "/auth"로 설정
@Slf4j // 로그 기록을 위한 Lombok 어노테이션
public class AuthController {

    private final SMSService smsService; // SMS 인증 서비스

    /**
     * 생성자 주입 방식으로 SMSService를 주입
     * @param smsService SMS 인증 서비스
     */
    public AuthController(SMSService smsService) {
        this.smsService = smsService;
    }

    /**
     * 인증번호 전송 API (POST 요청)
     *
     * - 사용자가 입력한 휴대폰 번호로 6자리 인증번호를 전송
     * - 인증번호를 세션에 저장하여 이후 검증할 수 있도록 함
     *
     * @param memberTell 사용자의 휴대폰 번호 (Request Parameter)
     * @param session 사용자 세션 (인증번호 저장)
     * @return 성공 시 {"message": "인증번호가 발송되었습니다."}, 실패 시 {"error": "핸드폰 번호를 입력하세요."}
     */
    @PostMapping("/send-code")
    public ResponseEntity<Map<String, String>> sendVerificationCode(@RequestParam String memberTell, HttpSession session) {
        //입력된 전화번호가 없는 경우 오류 반환
        if (memberTell == null || memberTell.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "핸드폰 번호를 입력하세요."));
        }

        //SMS 서비스 호출하여 인증번호 생성 및 전송
        String verificationCode = smsService.sendVerificationCode(memberTell);

        //인증번호를 세션에 저장하여 이후 검증할 때 사용
        session.setAttribute("verificationCode", verificationCode);
        System.out.println("인증번호 저장됨: " + verificationCode); // 디버깅용 로그 출력

        //성공 응답 반환
        return ResponseEntity.ok(Map.of("message", "인증번호가 발송되었습니다."));
    }

    /**
     * 인증번호 검증 API (POST 요청)
     *
     * - 사용자가 입력한 인증번호를 세션에 저장된 값과 비교
     * - 인증이 성공하면 세션에 "isVerified" 값을 true로 설정하여 이후 인증 확인 가능
     *
     * @param verificationCode 사용자가 입력한 인증번호
     * @param session 사용자 세션 (저장된 인증번호 확인)
     * @return 인증 성공 시 {"success": true}, 실패 시 {"success": false}
     */
    @PostMapping("/verify-code")
    public ResponseEntity<Map<String, Boolean>> verifyCode(@RequestParam String verificationCode, HttpSession session) {
        //세션에서 저장된 인증번호 가져오기
        String savedVerificationCode = (String) session.getAttribute("verificationCode");

        //디버깅 로그 and 핸드폰 인증없이 인증번호 확인 후 회원가입 진행하기위한 인증번호
        System.out.println("입력된 인증번호: " + verificationCode);
        System.out.println("세션에 저장된 인증번호: " + savedVerificationCode);

        //입력된 인증번호와 세션에 저장된 인증번호 비교
        if (savedVerificationCode != null && savedVerificationCode.equals(verificationCode)) {
            session.setAttribute("isVerified", true); // 인증 성공 시 세션에 인증 상태 저장
            return ResponseEntity.ok(Map.of("success", true)); // 인증 성공 응답 반환
        } else {
            return ResponseEntity.ok(Map.of("success", false)); // 인증 실패 응답 반환
        }
    }
}