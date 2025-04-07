package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.TagVO;
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
public class FeedListDTO {
    private Long id;
    private String feedType;      // 일반피드, 같이해요, 리얼후기
    private String feedContent;
    private String createdDate;
    private String updatedDate;
    private Long   memberId;
    private String memberNickname;
    private String memberFilePath;
    private String memberFileName;
    private Long   planId;
    private List<FileVO> files;
    private List<String> tags;

    public FeedVO toFeedVO() {
        FeedVO feedVO = new FeedVO();
        feedVO.setId(id);
        feedVO.setFeedContent(feedContent);
        return feedVO;
    }
}
