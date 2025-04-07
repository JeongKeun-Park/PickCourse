package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {

    // 전체 관리자 수
    int getCountAll(@Param("search") Search search);

    // 관리자 목록 조회
    List<AdminVO> getManageAdminList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    // 관리자 등록
    void postManageAdminList(AdminVO admin);

    // 관리자 추방
    void deleteManageAdminList(Long id);

    // 중복 체크
    int isAdminAccount(@Param("adminAccount") String adminAccount);

    // 조회(아이디/비밀번호) (mapper)
    public Optional<AdminVO> selectByAdminAccountAndAdminPassword(AdminVO adminVO);




}
