package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.dto.MemberFileDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.mapper.MemberMapper2;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO2 {
    private final MemberMapper2 memberMapper;

    public MemberFileDTO getMemberFileInfo(Long id) {
        return memberMapper.getMemberFileInfo(id);
    }
}
