package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.PlanByFeedListDTO;
import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.repository.FeedDAO;
import com.app.pickcourse.repository.RealFeedDAO;
import com.app.pickcourse.util.Pagination;
import jakarta.jws.Oneway;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RealFeedServiceTests {
    @Autowired
    private RealFeedService realFeedService;
    @Autowired
    private FeedDAO feedDAO;
    @Autowired
    private RealFeedDAO realFeedDAO;

    @Test
    public void testWriteReview() {
        FeedVO feedVO = new FeedVO();
        RealFeedDTO realFeedDTO = new RealFeedDTO();

        feedVO.setFeedContent("후기테스트1");
        feedDAO.save(feedVO);

        log.info(feedVO.getId().toString());

        realFeedDTO.setId(feedVO.getId());

        log.info(realFeedDTO.getId().toString());
        realFeedDTO.setPlanId(113L);
        realFeedDTO.setMemberId(1L);


        realFeedService.writeReview(feedVO, realFeedDTO);
    }

    @Test
    public void testFind () {
        log.info(realFeedService.getRealFeedList(127L).toString());
    }

    @Test
    public void testGetFeedPagination() {
        PlanByFeedListDTO planByFeedListDTO = new PlanByFeedListDTO();
        Pagination pagination = new Pagination();

        pagination.create(realFeedDAO.findFeedCountByPlanId(127L));


        log.info(realFeedService.getFeedPagination(pagination,127L).toString());
    }
}
