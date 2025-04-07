package com.app.pickcourse.DAO;

import com.app.pickcourse.domain.vo.WriteIncludeVO;
import com.app.pickcourse.repository.WriteIncludeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class WriteIncludeDAOTest {
    @Autowired
    private WriteIncludeDAO writeIncludeDAO;

    @Test
    public void testSave() {
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        for (int i = 0 ; i<5 ; i++) {
        writeIncludeVO.setPlanId(2L);
        writeIncludeVO.setIncludeContent("DAO테스트" +(i+1));
        writeIncludeDAO.save(writeIncludeVO);
        }

    }

    @Test
    public void testFindByPlanId() {
        writeIncludeDAO.findByPlanId(2L);
    }

    @Test
    public void testSetWriteInclude() {
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        writeIncludeVO.setPlanId(2L);
        writeIncludeVO.setId(11L);
        writeIncludeVO.setIncludeContent("변경된DAO1");

        writeIncludeDAO.setWriteInclude(writeIncludeVO);
    }

    @Test
    public void testDelete() {
        writeIncludeDAO.delete(11L);
    }

}
