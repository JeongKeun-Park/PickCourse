package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class FeedMapperTests {
    @Autowired
    private FeedMapper mapper;

    @Test
    public void postFeedWrite(){
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setFeedContent("아름다운 밤이에요test");
        mapper.postFeedWrite(feedDTO.toFeedVO());
    }

    @Test
    public void getFeedList(){
        List<FeedListDTO> list = mapper.getFeedList();
        list.forEach(System.out::println);
    }

    @Test
    public void getFeedModify(){
        Optional<FeedDTO> feedDTO = mapper.getFeedModify(51l);
        FeedDTO feed = feedDTO.orElseThrow(() -> new RuntimeException("FeedDTO not found"));
        log.info("feed:{}",feed);
    }

    @Test
    public void postFeedModify(){
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setId(51l);
        feedDTO.setFeedContent("정신 없는 낮 이에요test");
        mapper.PostFeedModify(feedDTO.toFeedVO());
    }

    @Test
    public void getFeedModifyList(){
        List<FeedListDTO> list = mapper.getFeedModifyList(52l);
        list.forEach(System.out::println);
    }

//    @Test void deleteFeedModifyList(){
//        mapper.deleteFeedModifyList(52l);
//    }
}
