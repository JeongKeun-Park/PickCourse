package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.RealStarVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RealStarMapperTests {
    @Autowired
    private RealStarMapper mapper;

    @Test
    public void postFeedWrite(){
        RealStarVO realStarVO = new RealStarVO();
        mapper.postFeedWrite(47l, 4);
    }

    @Test
    public void postReviewModify(){
        mapper.postReviewModify(3,47l);
    }
}
