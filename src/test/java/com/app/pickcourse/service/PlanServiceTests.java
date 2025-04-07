package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.PlanMapper;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.QuestionPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PlanServiceTests {
    @Autowired
    private PlanService planService;
    @Autowired
    private PlanDAO planDAO;
    @Autowired
    private WriteExcludeDAO writeExcludeDAO;
    @Autowired
    private WriteIncludeDAO writeIncludeDAO;
    @Autowired
    private WritePrepareDAO writePrepareDAO;
    @Autowired
    private ScheduleDAO scheduleDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private MemberService memberService;


    @Test
    public void testWritePlan() {
        PlanDTO planDTO = new PlanDTO();
        List<WriteExcludeDTO> test = new ArrayList<>();
        List<WriteIncludeDTO> test1 = new ArrayList<>();
        List<WritePrepareDTO> test2 = new ArrayList<>();
        List<ScheduleDTO> test3 = new ArrayList<>();
        WriteExcludeDTO writeExcludeVO = new WriteExcludeDTO();
        WriteExcludeDTO writeExcludeVO1 = new WriteExcludeDTO();
        WriteIncludeDTO writeIncludeVO = new WriteIncludeDTO();
        WriteIncludeDTO writeIncludeVO1 = new WriteIncludeDTO();
        WritePrepareDTO writePrepareVO = new WritePrepareDTO();
        WritePrepareDTO writePrepareVO1 = new WritePrepareDTO();
        ScheduleDTO scheduleVO = new ScheduleDTO();
        ScheduleDTO scheduleVO1 = new ScheduleDTO();

        planDTO.setPlanName("서비스테스트2");
        planDTO.setPlanStartDate("2025-03-04");
        planDTO.setPlanEndDate("2025-03-10");
        planDTO.setPlanDeadline("2025-03-20");
        planDTO.setPlanMaxPersonnel(10);
        planDTO.setPlanMinPersonnel(1);
        planDTO.setPlanPrice(25000);
        planDTO.setPlanStartAddress("서비스테스트2");
        planDTO.setPlanFileName("서비스테스트2");
        planDTO.setPlanFileSize("서비스테스트2");
        planDTO.setPlanFilePath("서비스테스트2");
        planDTO.setMemberId(1L);
        planDTO.setCourseId(1L);
        planDTO.setPlanContent("서비스테스트2");

        writeExcludeVO.setExcludeContent("test1");
        test.add(writeExcludeVO);

        writeExcludeVO1.setExcludeContent("test2");
        test.add(writeExcludeVO1);

        writeIncludeVO.setIncludeContent("test3");
        test1.add(writeIncludeVO);

        writeIncludeVO1.setIncludeContent("test4");
        test1.add(writeIncludeVO1);

        writePrepareVO.setPrepareContent("test5");
        test2.add(writePrepareVO);

        writePrepareVO1.setPrepareContent("test6");
        test2.add(writePrepareVO1);

        scheduleVO.setScheduleContent("test7");
        test3.add(scheduleVO);

        scheduleVO1.setScheduleContent("test8");
        test3.add(scheduleVO1);

        planDTO.setExcludeContents(test);
        planDTO.setIncludeContents(test1);
        planDTO.setPrepareContents(test2);
        planDTO.setScheduleContents(test3);

        planService.writePlan(planDTO);

        log.info(planDTO.toString());
    }

    @Test
    public void testFindMyPlan() {
        MyPLanListDTO myPlanListDTO = new MyPLanListDTO();
        Pagination pagination = new Pagination();

        pagination.create(planService.getTotal(7L));

        myPlanListDTO = planService.getMyPlanList(pagination, 7L);

         myPlanListDTO.getPlanList().forEach((planDTO) -> {
             log.info(planDTO.toString());
         });

         log.info(myPlanListDTO.toString());
    }

    @Test
    public void testFindPlanCount() {
        int count = planService.getTotal(1L);
        log.info("" + count);
    }

    @Test
    public void testGetPlanById() {
        PlanDTO planDTO = planService.getPlanById(113L).orElseThrow(()-> new RuntimeException());
        log.info(planDTO.toString());
    }

    @Test
    public void getPlanDetail() {
        planService.getPlanDetailById(111L);

        log.info(planService.getPlanDetailById(111L).toString());
    }

    @Test
    public void testWriteQuestion() {
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setPlanId(113L);
        questionDTO.setQuestionContent("서비스테스트2");
        questionDTO.setMemberId(1L);

        planService.writeQuestion(questionDTO);
    }

    @Test
    public void testFindQuestion() {
        QuestionListDTO questionListDTO = new QuestionListDTO();

        questionListDTO = planService.findQuestionLists(113L);

        log.info(questionListDTO.toString());

    }

    @Test
    public void testUpdatePoint(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(1L);
        memberDTO.setMemberPoint(15000);
        memberService.updatePoint(memberDTO);
    }

}
