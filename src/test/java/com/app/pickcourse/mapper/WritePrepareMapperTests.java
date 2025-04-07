package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WritePrepareDTO;
import com.app.pickcourse.domain.vo.WritePrepareVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class WritePrepareMapperTests {
    @Autowired
    private WritePrepareMapper writePrepareMapper;

    @Test
    public void testInsert() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();

        writePrepareVO.setPlanId(1L);
        writePrepareVO.setPrepareContent("테스트제목2");

        writePrepareMapper.insert(writePrepareVO);
    }

    @Test
    public void testSelectAll() {
        List<WritePrepareDTO> foundWritePrepare = writePrepareMapper.selectByPlanId(1L);
        log.info(foundWritePrepare.toString());
    }

    @Test
    public void testUpdate() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();

        writePrepareVO.setId(350L);
        writePrepareVO.setPlanId(113L);
        writePrepareVO.setPrepareContent("변경된내용1");

        writePrepareMapper.update(writePrepareVO);
    }

    @Test
    public void testDelete() {
        writePrepareMapper.delete(4L);
    }
}
