package com.app.pickcourse.domain.dto;

import com.app.pickcourse.util.Pagination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Getter
@ToString
@Setter
@NoArgsConstructor
public class PlanByFeedListDTO {
    private List<FeedListDTO> feedList;
    private Pagination pagination;
}
