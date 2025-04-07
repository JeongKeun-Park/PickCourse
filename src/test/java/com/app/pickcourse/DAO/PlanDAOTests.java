package com.app.pickcourse.DAO;

import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.repository.PlanDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PlanDAOTests {
    @Autowired
    private PlanDAO planDAO;

    @Test
    public void testSave (){
        PlanVO planVO = new PlanVO();
        PlanDTO planDTO = new PlanDTO();

        planDTO.setPlanName("제목3");
        planDTO.setPlanStartDate("2025-03-04");
        planDTO.setPlanEndDate("2025-03-10");
        planDTO.setPlanDeadline("2025-03-20");
        planDTO.setPlanMaxPersonnel(10);
        planDTO.setPlanMinPersonnel(1);
        planDTO.setPlanPrice(25000);
        planDTO.setPlanStartAddress("주소3");
        planDTO.setPlanFileName("파일이름3");
        planDTO.setPlanFileSize("파일크기3");
        planDTO.setPlanFilePath("파일경로3");
        planDTO.setMemberId(1L);
        planDTO.setCourseId(1L);
        planDTO.setPlanContent("내용3");

        planDAO.save(planDTO.toVO());
        log.info(planDTO.toString());
    }

    @Test
    public void testFindAll() {
        List<PlanVO> foundAll = planDAO.findAll();
        log.info(foundAll.toString());
    }

    @Test
    public void testFindById() {
        Optional<PlanDTO> found = planDAO.findById(1L);
        log.info(found.toString());
    }

    @Test
    public void testSetPlan() {
        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(1L);
        planDTO.setPlanName("DAO1");
        planDTO.setPlanStartDate("2025-03-08");
        planDTO.setPlanEndDate("2025-03-20");
        planDTO.setPlanDeadline("2025-03-07");
        planDTO.setPlanMaxPersonnel(20);
        planDTO.setPlanMinPersonnel(4);
        planDTO.setPlanPrice(10000);
        planDTO.setPlanStartAddress("DAO1");
        planDTO.setPlanContent("DAO1");
        planDTO.setPlanFilePath("DAO1");
        planDTO.setPlanFileSize("DAO1");
        planDTO.setPlanFileName("DAO1");
        planDTO.setUpdatedDate("2025-03-06");

        planDAO.setPlan(planDTO);
    }

    @Test
    public void testDelete() {
        planDAO.delete(1L);
    }
}
