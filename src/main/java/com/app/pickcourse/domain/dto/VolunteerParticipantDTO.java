package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.ParticipantVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter @Setter @ToString
public class VolunteerParticipantDTO {
    private Long   id;
    private Long   memberId;
    private String memberNickname;
    private String memberFilePath;
    private String memberFileName;
    private Long   planId;
    private String createdDate;
    private String updatedDate;

}
