package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ScheduleDTO;
import com.app.pickcourse.domain.vo.ScheduleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
//    추가
    public void insert(ScheduleVO scheduleVO);
//    플랜아이디로 조회
    public List<ScheduleDTO> selectByPlanId(Long planId);
//    수정
    public void update(ScheduleVO scheduleVO);
//    삭제
    public void delete(Long id);
//    status로 조회
    public List<ScheduleDTO> selectByStatus(Long planId);
}
