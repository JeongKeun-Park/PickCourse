package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ScheduleDTO;
import com.app.pickcourse.domain.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ScheduleMapperTests {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Test
    public void testInsert() {
        ScheduleVO scheduleVO = new ScheduleVO();

        for (int i = 0 ; i < 10 ; i++){
            scheduleVO.setScheduleContent("내용"+(i+1));
            scheduleVO.setPlanId(1L);

            scheduleMapper.insert(scheduleVO);
        }
    }

    @Test
    public void testSelectByPlanId() {
        List<ScheduleDTO> scheduleVOList = scheduleMapper.selectByPlanId(122L);
        log.info(scheduleVOList.toString());
    }

    @Test
    public void testUpdate() {
        ScheduleVO scheduleVO = new ScheduleVO();

        scheduleVO.setId(234L);
        scheduleVO.setPlanId(113L);
        scheduleVO.setScheduleContent("변경된내용1");

        scheduleMapper.update(scheduleVO);
    }

    @Test
    public void testDelete() {
        scheduleMapper.delete(18L);
    }
}
