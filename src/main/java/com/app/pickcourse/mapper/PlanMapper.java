package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.RecruitPlanDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanMapper {
//    추가
    public void insert(PlanVO planVO);

//    전체 조회
    public List<PlanVO> selectAll();

//    ID로 조회
    public Optional<PlanDTO> selectById(Long planId);

//    수정
    public void update(PlanDTO planDTO);

//    삭제
    public void deleteById(Long Id);

//    아이디별 전체조회
    public List<PlanDTO> selectAllById(Long id);

    public List<Long> selectRankingWeekly();
    public List<Long> selectRankingMonthly();
    public List<Long> selectRankingYearly();

//    멤버ID로 전체조회
    public List<PlanDTO> selectByMemberId(
            @Param("pagination") Pagination pagination,
            @Param("memberId") Long memberId);

//    목록개수
    public int selectCount(Long id);

//    코스ID로 전체조회
    public List<PlanDTO> selectByCourseId(Long courseId);

    List<RecruitPlanDTO> selectMyRecruitPlans(@Param("memberId") Long memberId,
                                              @Param("offset") int offset,
                                              @Param("limit") int limit);

    List<RecruitPlanDTO> selectMyRecruitPlansWithLimit(@Param("memberId") Long memberId,
                                                       @Param("offset") int offset,
                                                       @Param("limit") int limit);

}
