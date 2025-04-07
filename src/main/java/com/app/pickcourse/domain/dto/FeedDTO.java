package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.TagVO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedDTO {
    private Long id;
    private String feedType;
    private String feedContent;
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
