package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.ParticipantVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long planId;
    private String createdDate;
    private String updatedDate;

    public ParticipantVO toVO() {
        ParticipantVO participantVO = new ParticipantVO();
        participantVO.setId(id);
        participantVO.setMemberId(memberId);
        participantVO.setPlanId(planId);
        participantVO.setCreatedDate(createdDate);
        participantVO.setUpdatedDate(updatedDate);
        return participantVO;
    }
}
