package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlanDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String planName;
    private String planStartDate;
    private String planEndDate;
    private String planDeadline;
    private int planMaxPersonnel;
    private int planMinPersonnel;
    private int planPrice;
    private String planStartAddress;
    private String planContent;
    private String planFilePath;
    private String planFileSize;
    private String planFileName;
    private Long memberId;
    private Long courseId;
    private String CreatedDate;
    private String UpdatedDate;
    private List<WriteExcludeDTO> excludeContents;
    private List<WriteIncludeDTO> includeContents;
    private List<WritePrepareDTO> prepareContents;
    private List<ScheduleDTO> scheduleContents;
    private List<ParticipantDTO> participants;
    private List<Long> deleteIncludes;
    private List<Long> deleteExcludes;
    private List<Long> deletePrepares;
    private List<Long> deleteSchedules;
    private String memberNickname;
    private int memberAge;
    private List<FeedListDTO> feedList;

    public PlanVO toVO() {
        PlanVO planVO = new PlanVO();

        planVO.setId(id);
        planVO.setPlanName(planName);
        planVO.setPlanStartDate(planStartDate);
        planVO.setPlanEndDate(planEndDate);
        planVO.setPlanDeadline(planDeadline);
        planVO.setPlanMaxPersonnel(planMaxPersonnel);
        planVO.setPlanMinPersonnel(planMinPersonnel);
        planVO.setPlanPrice(planPrice);
        planVO.setPlanStartAddress(planStartAddress);
        planVO.setPlanContent(planContent);
        planVO.setPlanFilePath(planFilePath);
        planVO.setPlanFileSize(planFileSize);
        planVO.setPlanFileName(planFileName);
        planVO.setMemberId(memberId);
        planVO.setCourseId(courseId);
        planVO.setCreatedDate(CreatedDate);
        planVO.setUpdatedDate(UpdatedDate);

        return planVO;
    }
}
