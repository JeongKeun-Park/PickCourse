package com.app.pickcourse.domain.dto;

import com.app.pickcourse.util.Pagination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishPaginationDTO {
    private Pagination pagination;
    private List<WishPlanCourseDTO> wishList;
}
