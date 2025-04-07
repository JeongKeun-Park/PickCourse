package com.app.pickcourse.DAO;

import com.app.pickcourse.domain.dto.WritePrepareDTO;
import com.app.pickcourse.domain.vo.WritePrepareVO;
import com.app.pickcourse.repository.WriteIncludeDAO;
import com.app.pickcourse.repository.WritePrepareDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class WrtiePrepareDAOTests {
    @Autowired
    private WritePrepareDAO writePrepareDAO;

    @Test
    public void testSave() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();

        for (int i = 0 ; i<5 ; i++) {
        writePrepareVO.setPlanId(2L);
        writePrepareVO.setPrepareContent("DAO테스트" +(i+1));
        writePrepareDAO.save(writePrepareVO);
        }

    }

    @Test
    public void testFindByPlanId() {
        List<WritePrepareDTO> target = writePrepareDAO.findByPlanId(2L);
        log.info(target.toString());
    }

    @Test
    public void testSetWritePrepare() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();

        writePrepareVO.setPlanId(2L);
        writePrepareVO.setId(12L);
        writePrepareVO.setPrepareContent("변경된DAO1");

        writePrepareDAO.setWritePrepare(writePrepareVO);
    }

    @Test
    public void testDelete() {
        writePrepareDAO.delete(12L);
    }
}
