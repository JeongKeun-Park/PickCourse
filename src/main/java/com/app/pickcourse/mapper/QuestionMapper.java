package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.QuestionDTO;
import com.app.pickcourse.domain.vo.QuestionVO;
import com.app.pickcourse.util.QuestionPagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
//    질문 등록
    public void insertQuestion(QuestionVO questionVO);

//    planId의 목록 조회
    public List<QuestionDTO> selectQuestion(Long planId);

//    planId의 전체 질문개수
    public int selectCountByPlanId(Long planId);
}
