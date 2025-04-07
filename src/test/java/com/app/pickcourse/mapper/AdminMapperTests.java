package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminMapperTests {
    @Autowired
    private AdminMapper mapper;

    @Test
    public void getManageListNotice() {
        Search search = new Search();
        search.setType("a");
        search.setKeyWord("KANG");
        Pagination pagination = new Pagination();
        List<AdminVO> notices = mapper.getManageAdminList(pagination, search);
        log.info(pagination.toString());
        notices.forEach(System.out::println);
        log.info("notices size :: {} ", notices.size());
    }

    @Test
    public void postManageAdmin() {
        AdminVO admin = new AdminVO();
        admin.setAdminAccount("hard");
        admin.setAdminPassword("1234");
        admin.setAdminName("cho");
        admin.setAdminPhone("011");
        admin.setAdminEmail("daum");
        mapper.postManageAdminList(admin);
    }

    @Test
    public void deleteManageAdmin() {
        mapper.deleteManageAdminList(3l);
    }
}
