package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FileVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
    private int    realStar;
    private Long   memberId;
    private Long   planId;
    private List<FileVO> files;
}
