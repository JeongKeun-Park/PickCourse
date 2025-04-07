package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.PlanByFeedListDTO;
import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RealFeedService {
    private final RealFeedDAO realFeedDAO;
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;
    private final PlanDAO planDAO;
    private final TagDAO tagDAO;
    private final RealFileDAO realFileDAO;

    //    여행후기 작성
    public void writeReview(@Param("feedVO") FeedVO feedVO, @Param("realFeedDTO") RealFeedDTO realFeedDTO) {

        realFeedDAO.saveReview(feedVO, realFeedDTO);
    }
//    후기목록 조회
public List<FeedListDTO> getRealFeedList(Long planId) {
    List<FeedListDTO> list = null;
    list = realFeedDAO.findFeedListByPlanId(planId);


    // tag, 파일 가져오기
    list.forEach(feed -> {
        feed.setTags(tagDAO.getFeedModify(feed.getId()));
        List<FileVO> fileList = null;
        fileList = realFileDAO.getRealModify(feed.getId());
        feed.setFiles(fileList);
    });

    return list;
}

// 페이지네이션
    public PlanByFeedListDTO getFeedPagination (Pagination pagination, Long planId) {
        PlanByFeedListDTO planByFeedListDTO = new PlanByFeedListDTO();

        pagination.create(realFeedDAO.findFeedCountByPlanId(planId));

        planByFeedListDTO.setPagination(pagination);
        planByFeedListDTO.setFeedList(realFeedDAO.findPaginationByPlanId(pagination, planId));

        planByFeedListDTO.getFeedList().forEach((feed) -> {
            feed.setTags(tagDAO.getFeedModify(feed.getId()));
            feed.setFiles(realFileDAO.getRealModify(feed.getId()));
        });

        return planByFeedListDTO;
    }
}
