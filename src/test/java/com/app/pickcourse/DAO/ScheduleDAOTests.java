package com.app.pickcourse.DAO;

import com.app.pickcourse.domain.dto.ScheduleDTO;
import com.app.pickcourse.domain.vo.ScheduleVO;
import com.app.pickcourse.repository.ScheduleDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ScheduleDAOTests {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Test
    public void testSave() {
        ScheduleVO scheduleVO = new ScheduleVO();

        scheduleVO.setScheduleContent("DAO테스트2");
        scheduleVO.setPlanId(2L);

        scheduleDAO.save(scheduleVO);
    }

    @Test
    public void testFindByPlanId() {
        List<ScheduleDTO> foundSchedule = scheduleDAO.findByPlanId(2L);
        log.info(foundSchedule.toString());
    }

    @Test
    public void testSetSchedule() {
        ScheduleVO scheduleVO = new ScheduleVO();

        scheduleVO.setId(11L);
        scheduleVO.setScheduleContent("변경된DAO1");

        scheduleDAO.setSchedule(scheduleVO);
    }

    @Test
    public void testDelete() {
        scheduleDAO.delete(11L);
    }
}
