package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WritePrepareVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WritePrepareDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String prepareContent;
    private Long planId;

    public WritePrepareVO toVO() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();
        writePrepareVO.setId(id);
        writePrepareVO.setPrepareContent(prepareContent);
        writePrepareVO.setPlanId(planId);

        return writePrepareVO;
    }

}
