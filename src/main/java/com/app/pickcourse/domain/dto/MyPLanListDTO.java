package com.app.pickcourse.domain.dto;

import com.app.pickcourse.util.Pagination;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyPLanListDTO {
    private Pagination pagination;
    private List<PlanDTO> planList;
}
