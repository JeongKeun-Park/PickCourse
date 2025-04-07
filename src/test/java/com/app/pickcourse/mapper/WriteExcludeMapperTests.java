package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.WriteExcludeDTO;
import com.app.pickcourse.domain.vo.WriteExcludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class WriteExcludeMapperTests {
    @Autowired
    private WriteExcludeMapper writeExcludeMapper;

    @Test
    public void testInsert() {
        WriteExcludeVO exclude = new WriteExcludeVO();

        exclude.setExcludeContent("내용2");
        exclude.setPlanId(1L);

        writeExcludeMapper.insert(exclude);
    }

    @Test
    public void testSelectAll() {
        WriteExcludeVO exclude = new WriteExcludeVO();

        List<WriteExcludeDTO> foundWriteExclude = writeExcludeMapper.selectByPlanId(2L);
        log.info(foundWriteExclude.toString());
    }

    @Test
    public void testUpdate() {
        WriteExcludeVO exclude = new WriteExcludeVO();

        exclude.setPlanId(113L);
        exclude.setExcludeContent("변경된내용1");
        exclude.setId(339L);

        writeExcludeMapper.update(exclude);
    }

    @Test
    public void testDelete() {
        WriteExcludeVO exclude = new WriteExcludeVO();

        writeExcludeMapper.delete(2L);
    }
}
