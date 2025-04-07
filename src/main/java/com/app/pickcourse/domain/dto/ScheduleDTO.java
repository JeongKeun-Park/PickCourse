package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.ScheduleVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScheduleDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String scheduleContent;
    private Long planId;
    private String status;

    public ScheduleVO toVO (){
        ScheduleVO scheduleVO = new ScheduleVO();

        scheduleVO.setId(id);
        scheduleVO.setScheduleContent(scheduleContent);
        scheduleVO.setPlanId(planId);
        scheduleVO.setStatus(status);

        return scheduleVO;
    }
}
