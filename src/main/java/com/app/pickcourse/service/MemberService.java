package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    private final MemberDAO memberDAO;
    private final MemberMapper memberMapper;

    //    회원가입
    public void kakaoJoin(MemberDTO memberDTO){
        memberDAO.kakaoSave(memberDTO.toVO());
    }

    public void join(MemberDTO memberDTO){
        memberDAO.save(memberDTO.toVO());
    }

    //    이메일로 회원 조회
    public Optional<MemberDTO> getMember(String memberEmail) {
        return memberDAO.findByMemberEmail(memberEmail);
    }

    public void update(MemberDTO memberDTO){
        Optional<MemberDTO> member = memberDAO.findById(memberDTO.getId());

        memberDTO.setMemberEmail(member.get().getMemberEmail());
        memberDTO.setMemberTell(member.get().getMemberTell());

        memberDAO.set(memberDTO.toVO());
    }

    // 회원 삭제
    public void delete(Long id) {memberDAO.delete(id);}

    // 이메일 로그인
    public Optional<MemberDTO> login(MemberDTO memberDTO) {
        return memberDAO.findByMemberEmailAndPassword(memberDTO);
    }

    // 닉네임 중복검사
    public Optional<MemberVO> getMemberByNickname(String nickname) {
        return memberMapper.findByNickname(nickname);
    }


    public boolean checkPassword(Long id, String oldPassword) {
        Optional<MemberDTO> optionalMember = memberMapper.selectById(id);

        if (optionalMember.isEmpty()) {
            return false; // 존재하지 않는 회원이면 false 반환
        }

        MemberDTO member = optionalMember.get();

        // 🚨 기존 비밀번호가 틀릴 경우, 예외를 던지지 않고 false 반환
        return member.getMemberPassword().equals(oldPassword);
    }
//    비밀번호 변경
    public void updatePassword(Long userId, String newPassword) {
        memberMapper.updatePassword(userId, newPassword);
    }

    public boolean checkNicknameDuplicate(String memberNickname) {
        return memberDAO.checkNicknameDuplicate(memberNickname);
    }

    public Optional<MemberDTO> findEmailByNickname(String memberNickname) {
        return memberDAO.findEmailByNickname(memberNickname);
    }

//    프로필사진
    public void updateMemberFile(MemberDTO memberDTO) {
        memberDAO.updateMemberFile(memberDTO);
    }
//  프사
    public MemberDTO uploadProfile(Long id, MultipartFile file){
//            파일 업로드
            if(file.getOriginalFilename().equals("")){
                return null;
            }
            String todayPath = getPath();
            String rootPath = "/upload/" + todayPath;
            String fileName = null;
            UUID uuid = UUID.randomUUID();

            try {
                File directory = new File(rootPath);
                if(!directory.exists()){
                    directory.mkdirs();
                }

                file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));

//            썸네일 가공
                if(file.getContentType().startsWith("image")){
                    fileName = "t_" + uuid.toString() + "_" + file.getOriginalFilename();
                    FileOutputStream out = new FileOutputStream(new File(rootPath, fileName));
                    Thumbnailator.createThumbnail(file.getInputStream(), out, 100, 100);
                    out.close();

                    MemberDTO member = memberDAO.findById(id).orElseThrow(
                            () -> new RuntimeException("회원 정보 없음")
                    );
                    member.setId(id);
                    member.setMemberFilePath(todayPath);
                    member.setMemberFileName(fileName);
                    member.setMemberFileSize(String.valueOf(file.getSize()));

                    memberDAO.updateMemberFile(member);

                    return member;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public MemberDTO getMemberById(Long id){
        return memberDAO.findById(id).orElseThrow(
                () -> new RuntimeException("해당 ID의 회원 정보를 찾을 수 없습니다: " + id)
        );
    }

//    포인트 변경
    public void updatePoint(MemberDTO memberDTO) {
        MemberVO memberVO = memberDTO.toVO();
        memberDAO.updatePoint(memberVO);
    }


}

