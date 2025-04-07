package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.RealFeedVO;
import com.app.pickcourse.domain.vo.RealFileVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RealFeedDTO {
    private Long id;
    private Long memberId;
    private Long planId;
    private List<RealFileVO> realFiles;

    public RealFeedVO toVO() {
        RealFeedVO vo = new RealFeedVO();

        vo.setId(id);
        vo.setMemberId(memberId);
        vo.setPlanId(planId);

        return vo;
    }
}
