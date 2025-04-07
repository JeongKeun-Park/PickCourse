package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WriteIncludeVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WriteIncludeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String includeContent;
    private Long planId;

    public WriteIncludeVO toVO(){
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        writeIncludeVO.setId(id);
        writeIncludeVO.setIncludeContent(includeContent);
        writeIncludeVO.setPlanId(planId);

        return writeIncludeVO;
    }
}
