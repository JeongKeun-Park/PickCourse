package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    // 전체 회원 수
    int getCountAll(@Param("search") Search search);

    // 회원 정지
    void patchMemberListPause(@Param("id") Long id);

    // 회원 정지 해제
    void patchMemberListRestart(@Param("id") Long id);

    // 회원 목록 조회
    List<MemberVO> getMemberList(@Param("pagination") Pagination pagination, @Param("search") Search search);
    // 활동회원 목록 조회
    List<MemberVO> getMemberListActY(@Param("pagination") Pagination pagination, @Param("search") Search search);
    // 활동 정지 회원 목록 조회
    List<MemberVO> getMemberListActN(@Param("pagination") Pagination pagination, @Param("search") Search search);

    // 회원 추방
    void deleteMemberList(Long id);

    //    추가하기
    public void insert(MemberVO memberVO);

    //    조회(아이디/비밀번호)
    public Optional<MemberDTO> selectByMemberEmailAndMemberPassword(MemberDTO memberDTO);

    //    이메일 중복 검사
    public int selectCountByMemberEmail(String memberEmail);

    //    조회(회원 번호)
    public Optional<MemberDTO> selectById(Long id);

    //    수정
    public void update(MemberVO memberVO);

    //    삭제
    public void delete(Long id);

//    카카오 회원가입
    public void kakaoInsert(MemberVO memberVO);

    //    조회(이메일)
    public Optional<MemberDTO> selectByMemberEmail(String memberEmail);

    // 이메일로 사용자 ID 조회
    Optional<Long> selectIdByEmail(String email);

    // 닉네임 중복검사
    public Optional<MemberVO> findByNickname(String nickname);

    // 비밀번호 조회
    String getCurrentPassword(Long id);

    // 비밀번호 변경하기
    void updatePassword(@Param("id") Long id, @Param("memberPassword") String newPassword);

    int countByNickname(@Param("memberNickname") String memberNickname);

    // 닉네임으로 이메일조회(답장기능)

    Optional<MemberDTO> findEmailByNickname(String memberNickname);

//    회원 프로필사진 업데이트
    void updateMemberFile(MemberDTO memberDTO);

//    포인트수정
    public void updatePoint(MemberVO memberVO);

}
