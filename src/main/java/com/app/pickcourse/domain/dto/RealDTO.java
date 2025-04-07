package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RealDTO {
    private Long id;
    private String feedContent;
    private Long planId;
    private List<String> tags;
    private List<FileVO> files;
    private List<Long> deleteFileId;
    private String createDate;
    private String updateDate;

    public FeedVO toFeedVO() {
        FeedVO feedVO = new FeedVO();
        feedVO.setId(id);
        feedVO.setFeedContent(feedContent);
        return feedVO;
    }
}
