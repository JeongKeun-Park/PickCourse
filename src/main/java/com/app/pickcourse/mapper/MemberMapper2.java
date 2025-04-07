package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.dto.MemberFileDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper2 {

    // 멤버의 파일 정보 가져오기
    MemberFileDTO getMemberFileInfo(Long id);
}
