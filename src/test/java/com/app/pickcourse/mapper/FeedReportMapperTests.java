package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.ReportIdVO;
import com.app.pickcourse.domain.vo.ReportVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FeedReportMapperTests {
    @Autowired
    private FeedReportMapper mapper;
    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void saveFeedReport() {
        ReportIdVO reportVO = new ReportIdVO();
        reportMapper.postReportReplyList(reportVO);

        mapper.saveFeedReport(reportVO.getId(), 1l, 1l);

    }


}
