package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.mapper.AdminMapper;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminDAO {
    private final AdminMapper adminMapper;
    private final MemberMapper memberMapper;

    public int getCountAll(Search search) {
        return adminMapper.getCountAll(search);
    }

    public List<AdminVO> getManageAdminList(Pagination pagination, Search search) {
        return adminMapper.getManageAdminList(pagination, search);
    }

    public int isAdminAccount(String adminAccount) {
        return adminMapper.isAdminAccount(adminAccount);
    }

    public void postManageAdminList(AdminVO adminVO) {
        adminMapper.postManageAdminList(adminVO);
    }

    public void deleteManageAdminList(Long adminId) {
        adminMapper.deleteManageAdminList(adminId);
    }

        // 로그인 (DAO)
    public Optional<AdminVO> findByAdminAccountAndPassword(AdminVO adminVO){
        return adminMapper.selectByAdminAccountAndAdminPassword(adminVO);
    }
}
