package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WriteIncludeDTO;
import com.app.pickcourse.domain.vo.WriteExcludeVO;
import com.app.pickcourse.domain.vo.WriteIncludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class WriteIncludeMapperTests {
    @Autowired
    private WriteIncludeMapper writeIncludeMapper;

    @Test
    public void testInsert() {
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        writeIncludeVO.setPlanId(1L);
        writeIncludeVO.setIncludeContent("포함내용2");

        writeIncludeMapper.insert(writeIncludeVO);
    }

    @Test
    public void testSelectAll() {
        List<WriteIncludeDTO> foundWriteInclude = writeIncludeMapper.selectByPlanId(2L);
        log.info(foundWriteInclude.toString());
    }

    @Test
    public void testUpdate() {
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        writeIncludeVO.setId(343L);
        writeIncludeVO.setPlanId(113L);
        writeIncludeVO.setIncludeContent("변경된내용1");

        writeIncludeMapper.update(writeIncludeVO);

    }

    @Test
    public void testDelete() {
        writeIncludeMapper.delete(3L);
    }
}
