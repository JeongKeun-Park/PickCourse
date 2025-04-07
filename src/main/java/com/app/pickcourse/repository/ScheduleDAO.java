package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ScheduleDTO;
import com.app.pickcourse.domain.vo.ScheduleVO;
import com.app.pickcourse.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleDAO {
    private final ScheduleMapper scheduleMapper;

//    추가
    public void save(ScheduleVO scheduleVO) {
        scheduleMapper.insert(scheduleVO);
    }
//    플랜아이디로 조회
    public List<ScheduleDTO> findByPlanId(Long planId) {
        return scheduleMapper.selectByPlanId(planId);
    }
//    수정
    public void setSchedule(ScheduleVO scheduleVO) {
        scheduleMapper.update(scheduleVO);
    }
//    삭제
    public void delete(Long id) {
        scheduleMapper.delete(id);
    }

}
