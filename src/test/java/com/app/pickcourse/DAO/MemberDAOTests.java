package com.app.pickcourse.dao;

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
public class MemberDAOTests {

    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberVO memberVO;
    @Autowired
    private MemberDTO memberDTO;

    @Test
    public void saveTest() {
        memberVO =  new MemberVO();
        memberVO.setMemberEmail("dao2@test.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberNickname("다오테스트2");
        memberVO.setMemberTell("010-9999-9999");
        memberVO.setMemberBirth("2002-11-11");
        memberVO.setMemberGender("여자");

        memberDAO.save(memberVO);
        log.info(memberVO.toString());
    }

    @Test
    public void findByMemberEmailTest() {
        Optional<MemberDTO> foundMember = memberDAO.findByMemberEmail("dao2@test.com");
        log.info(foundMember.toString());
    }

    @Test
    public void updateTest() {

        memberVO.setMemberNickname("다오수정테스트");
        memberVO.setMemberTell("번호 없음");
        memberVO.setMemberBirth("1919-11-11");
        memberVO.setMemberGender("남자");
        memberVO.setId(2L);

        memberDAO.set(memberVO);

        Optional<MemberDTO> updatedMember = memberDAO.findByMemberEmail("dao@test.com");
        log.info(updatedMember.toString());
    }

//    @Test
//    public void deleteTest() {
//        memberVO.setId(3L);
//        memberDAO.delete(memberVO);
//    }

    @Test
    public void findByMemberEmailAndPasswordTest() {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("dao@test.com");
        memberDTO.setMemberPassword("1234");

        Optional<MemberDTO> result = memberDAO.findByMemberEmailAndPassword(memberDTO);

        log.info("조회된 회원 정보: {}", result);
    }


}
