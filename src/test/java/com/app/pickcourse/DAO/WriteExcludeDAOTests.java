package com.app.pickcourse.DAO;

import com.app.pickcourse.domain.dto.WriteExcludeDTO;
import com.app.pickcourse.domain.vo.WriteExcludeVO;
import com.app.pickcourse.mapper.WriteExcludeMapper;
import com.app.pickcourse.repository.WriteExcludeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class WriteExcludeDAOTests {
    @Autowired
    private WriteExcludeDAO writeExcludeDAO;

    @Test
    public void testSave() {
        WriteExcludeVO writeExcludeVO = new WriteExcludeVO();

        writeExcludeVO.setPlanId(2L);
        writeExcludeVO.setExcludeContent("DAO테스트2");

        writeExcludeDAO.save(writeExcludeVO);
    }

    @Test
    public void testSelectByPlanId() {
        List<WriteExcludeDTO> foundWriteExclude =  writeExcludeDAO.findByPlanId(1L);
        log.info(foundWriteExclude.toString());
    }

    @Test
    public void testSetWriteExclude() {
        WriteExcludeVO writeExcludeVO = new WriteExcludeVO();

        writeExcludeVO.setPlanId(2L);
        writeExcludeVO.setId(8L);
        writeExcludeVO.setExcludeContent("변경된DAO 1");

        writeExcludeDAO.setWriteExclude(writeExcludeVO);
    }

    @Test
    public void testDelete() {
        writeExcludeDAO.delete(10L);
    }
}
