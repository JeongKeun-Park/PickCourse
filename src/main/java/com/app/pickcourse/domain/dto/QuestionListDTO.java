package com.app.pickcourse.domain.dto;

import com.app.pickcourse.util.QuestionPagination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter@Setter@ToString
public class QuestionListDTO {
    private int count;
    private List<QuestionDTO> questionList;

    public QuestionListDTO() {
        this.count = 1;
    }
}
