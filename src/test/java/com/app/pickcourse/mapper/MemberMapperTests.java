package com.app.pickcourse.mapper;


import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;




@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired

    MemberMapper mapper;

    @Test
    public void getMemberListTest() {
        Search search = new Search();
        search.setType("e");
        search.setKeyWord("3");
        Pagination pagination = new Pagination();
        List<MemberVO> memberList = mapper.getMemberList(pagination, search);
        memberList.forEach(System.out::println);
        log.info("memberList size: {}", memberList.size());
        log.info("pagination: {}", pagination);
    }

    @Test
    public void postMemberListTest() {
        mapper.patchMemberListPause(3l);
    }

    @Test
    public void deleteMemberListTest() {
        mapper.deleteMemberList(3l);
    }

    private MemberMapper memberMapper;

    @Test
    public void testInsert() {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("hhh1");
        memberVO.setMemberPassword("1234");

        mapper.insert(memberVO);

    }

    @Test
    public void testSelectCountByMemberEmail(){
        int status = mapper.selectCountByMemberEmail("test@naver.com");
        log.info("status:{}", status);
    }

    @Test
    public void testSelectByMemberEmailAndMemberPassword(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("qwe@123.com");
        memberDTO.setMemberPassword("qwe12341234");
        MemberDTO foundMember = mapper.selectByMemberEmailAndMemberPassword(memberDTO).orElse(null);
        log.info(foundMember.toString());
    }

    @Test
    public void testUpdate() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberNickname("홍길동");
        memberVO.setMemberTell("010-1111-1234");
        memberVO.setMemberBirth("1919-11-11");
        memberVO.setMemberGender("남자");
        memberVO.setId(1L);

        mapper.update(memberVO);
    }

    @Test
    public void testDelete() {
        mapper.delete(23L);
    }

    @Test
    public void testUpdatePoint() {
        MemberVO memberVO = new MemberVO();

        memberVO.setId(1L);
        memberVO.setMemberPoint(20000);

        mapper.updatePoint(memberVO);
    }


}
