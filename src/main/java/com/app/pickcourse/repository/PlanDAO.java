package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.RecruitPlanDTO;
import com.app.pickcourse.domain.dto.TourListDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.mapper.PlanMapper;
import com.app.pickcourse.mapper.ScheduleMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanDAO {
    private final PlanMapper planMapper;
//  추가
    public void save(PlanVO planVO) {
        planMapper.insert(planVO);
    }
//    전체조회
    public List<PlanVO> findAll() {
        return planMapper.selectAll();
    }
//    ID로 조회
    public Optional<PlanDTO> findById(Long id) {
        return planMapper.selectById(id);
    }
//    수정
    public void setPlan(PlanDTO planDTO) {
        planMapper.update(planDTO);
    }
//    삭제
    public void delete(Long id) {
        planMapper.deleteById(id);
    }


//   MEMBER ID로 목록 조회
    public List<PlanDTO> findMyPlan(
            @Param("pagination") Pagination pagination,
            @Param("memberId") Long id) {
        return planMapper.selectByMemberId(pagination, id);
    }

//    갯수 조회
    public int findTotal(Long id) {
        return planMapper.selectCount(id);
    }

//    코스ID로 전체 조회
    public List<PlanDTO> findByCourseId(Long courseId) {
        return planMapper.selectByCourseId(courseId);
    }

//    멤버ID로 전체 조회
    public List<PlanDTO> findByMemberId(Long memberId) {
        return planMapper.selectAllById(memberId);
    }

    public List<RecruitPlanDTO> findMyRecruitPlans(Long memberId, int offset, int limit) {
        return planMapper.selectMyRecruitPlans(memberId, offset, limit);
    }

    public List<RecruitPlanDTO> findByMemberIdWithLimit(Long memberId, int offset, int limit) {
        return planMapper.selectMyRecruitPlansWithLimit(memberId, offset, limit);
    }
}
