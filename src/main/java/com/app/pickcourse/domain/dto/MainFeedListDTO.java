package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter@Setter@ToString
@NoArgsConstructor
public class MainFeedListDTO {
    private Long id;
    private String feedContent;
    private String createdDate;
    private String updatedDate;
    private Long   memberId;
    private MemberVO member;
    private List<FileVO> files;
    private List<String> tags;
}
