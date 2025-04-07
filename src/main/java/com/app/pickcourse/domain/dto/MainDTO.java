package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.CourseVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter@Setter@ToString
@NoArgsConstructor
public class MainDTO {
    private List<MainCourseDTO> courses;
    private List<MainFeedListDTO> feeds;
    private MainCourseDTO volunteer;
}
