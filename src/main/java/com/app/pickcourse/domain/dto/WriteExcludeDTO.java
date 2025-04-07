package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WriteExcludeVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WriteExcludeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String excludeContent;
    private Long planId;

    public WriteExcludeVO toVO() {
        WriteExcludeVO writeExcludeVO = new WriteExcludeVO();

        writeExcludeVO.setId(id);
        writeExcludeVO.setPlanId(planId);
        writeExcludeVO.setExcludeContent(excludeContent);

        return writeExcludeVO;
    }
}
