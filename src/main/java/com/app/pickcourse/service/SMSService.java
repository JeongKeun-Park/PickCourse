package com.app.pickcourse.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Random;

/**
 * SMS 인증번호 발송 서비스
 * CoolSMS API를 사용하여 인증번호를 발송하는 서비스 클래스
 * 회원가입 및 비밀번호 찾기 등에서 사용 가능
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class) // 예외 발생 시 롤백 처리
public class SMSService {

    //CoolSMS API Key & Secret Key
    private final String apiKey = "";  // 여기에 API 키 입력
    private final String apiSecret = "";  // 여기에 API Secret 입력

    /**
     * 인증번호 전송 메서드
     * 입력받은 휴대폰 번호로 6자리 랜덤 인증번호를 전송
     * CoolSMS API를 사용하여 문자 메시지를 발송
     *
     * @param memberTell 사용자의 휴대폰 번호
     * @return 생성된 인증번호 (회원이 입력한 값과 비교하기 위해 반환)
     */
    public String sendVerificationCode(String memberTell) {
        //CoolSMS API 객체 생성
        Message coolsms = new Message(apiKey, apiSecret);

        //6자리 인증번호 생성
        String verificationCode = generateRandomCode();

        //CoolSMS API에 전달할 파라미터 설정
        HashMap<String, String> params = new HashMap<>();
        params.put("to", memberTell); // 수신자 전화번호
        params.put("from", "01066629790"); // 발신자 전화번호
        params.put("type", "SMS"); // 문자 메시지 유형 (SMS)
        params.put("text", "[인증번호] " + verificationCode + "를 입력해주세요."); // 전송할 메시지
        params.put("app_version", "Signup Verification 1.0"); // 앱 버전 정보 (필수 아님)

        try {
            //CoolSMS API 호출 (문자 전송)
            JSONObject response = coolsms.send(params);
            System.out.println("SMS 전송 결과: " + response.toJSONString()); // 성공 로그 출력
        } catch (CoolsmsException e) {
            System.out.println("SMS 전송 실패: " + e.getMessage()); // 실패 시 에러 메시지 출력
        }

        return verificationCode; // 생성된 인증번호 반환 (세션 저장 등 활용 가능)
    }

    /**
     * 6자리 랜덤 인증번호 생성
     * 0~9까지의 숫자를 조합하여 6자리 문자열로 반환
     * SMS 인증, 비밀번호 찾기 등에 사용
     *
     * @return 6자리 랜덤 숫자 문자열
     */
    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        //6자리 숫자를 무작위로 생성하여 문자열로 조합
        for (int i = 0; i < 6; i++) {
            codeBuilder.append(random.nextInt(10)); // 0~9까지 랜덤 숫자 추가
        }

        return codeBuilder.toString(); // 최종 인증번호 반환
    }
}
