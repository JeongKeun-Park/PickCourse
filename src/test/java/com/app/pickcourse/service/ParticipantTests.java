package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ParticipantDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ParticipantTests {
    @Autowired
    private ParticipantService participantService;

    @Test
    public void testInsert () {
        ParticipantDTO participantDTO = new ParticipantDTO();

        participantDTO.setMemberId(1L);
        participantDTO.setPlanId(128L);

        participantService.insertParticipant(participantDTO);
    }
}
