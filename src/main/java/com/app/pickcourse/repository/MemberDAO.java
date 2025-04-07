package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;


    //    전체 건수
    public int getCountAll(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    활동회원 전체 건수
    public int getCountAllActY(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    활동정지 회원 전체 건수
    public int getCountAllActN(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    전체 멤버 조회
    public List<MemberVO> getMemberList(Pagination pagination, Search search) {
        return memberMapper.getMemberList(pagination, search);
    }
    //    활동회원 전체 멤버 조회
    public List<MemberVO> getMemberListActY(Pagination pagination, Search search) {
        return memberMapper.getMemberListActY(pagination, search);
    }
    //    활동정지 회원 전체 멤버 조회
    public List<MemberVO> getMemberListActN(Pagination pagination, Search search) {
        return memberMapper.getMemberListActN(pagination, search);
    }

    //    추가
    public void kakaoSave(MemberVO memberVO){
        memberMapper.kakaoInsert(memberVO);
    }

    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }
    //    수정
    public void set(MemberVO memberVO){
        memberMapper.update(memberVO);
    }
    //    삭제
    public void delete(Long id){memberMapper.delete(id);}

    //    조회(이메일)
    public Optional<MemberDTO> findByMemberEmail(String memberEmail){
        return memberMapper.selectByMemberEmail(memberEmail);
    }

    // 이메일로 아이디 조회
    public Optional<Long> findIdByEmail(String email) {
        return memberMapper.selectIdByEmail(email);
    }

//    로그인
    public Optional<MemberDTO> findByMemberEmailAndPassword(MemberDTO memberDTO){
        return memberMapper.selectByMemberEmailAndMemberPassword(memberDTO);
    }

//    관리자 :: 회원 정지
    public void patchMemberListPause(Long memberId) {
        memberMapper.patchMemberListPause(memberId);
    }

//    관리자 :: 회원 정지 해제
    public void patchMemberListRestart(Long memberId) {
        memberMapper.patchMemberListRestart(memberId);
    }

//    관리자 :: 회원 추방
    public void deleteMemberList(Long memberId) {
        memberMapper.deleteMemberList(memberId);
    }

//    ID로 회원 조회
    public Optional<MemberDTO> findById(Long id) {
        return memberMapper.selectById(id);
    }

    // 기존 비밀번호 조회
    public String getCurrentPassword(Long id) {
        return memberMapper.getCurrentPassword(id);
    }

    // 비밀번호 변경
    public void updatePassword(Long id, String newPassword) {
        memberMapper.updatePassword(id, newPassword);
    }

    public boolean checkNicknameDuplicate(String memberNickname) {
        return memberMapper.countByNickname(memberNickname) > 0;
    }
    public Optional<MemberDTO> findEmailByNickname(String memberNickname) {return memberMapper.findEmailByNickname(memberNickname);}

//    프로필사진
    public void updateMemberFile(MemberDTO memberDTO) {
        memberMapper.updateMemberFile(memberDTO);
    }

//    포인트 변경
    public void updatePoint (MemberVO memberVO) {
        memberMapper.updatePoint(memberVO);
    }

}
