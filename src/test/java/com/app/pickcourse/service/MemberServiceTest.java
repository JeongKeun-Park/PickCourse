package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.repository.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberDTO memberDTO;

    @Test
    public void joinTest(){
        memberDTO = new MemberDTO();

        memberDTO.setMemberEmail("newtest@test.new");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberNickname("서비스테스트");
        memberDTO.setMemberTell("111-1111-1111");
        memberDTO.setMemberBirth("1111-11-11");
        memberDTO.setMemberGender("선택안함");

        memberService.join(memberDTO);
        log.info(memberDTO.toString());
    }

    @Test
    public void getMemberTest(){
        memberDTO.setMemberEmail("newtest@test.new");
        Optional<MemberDTO> member = memberService.getMember(memberDTO.getMemberEmail());
        log.info(member.toString());
    }

    @Test
    public void updateTest(){
        memberDTO.setMemberNickname("홍길동");
        memberDTO.setMemberTell("010-1111-1234");
        memberDTO.setMemberBirth("1919-11-11");
        memberDTO.setMemberGender("남자");
        memberDTO.setId(4L);

        memberService.update(memberDTO);
        log.info(memberDTO.toString());
    }

    @Test
    public void deleteTest(){
        memberDTO.setId(4L);
        memberService.delete(4L);
    }


}
