package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TogetherFileMapperTests {
    @Autowired
    private TogetherFileMapper mapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private TogetherFeedMapper togetherFeedMapper;
    @Autowired
    private TagMapper feedTagMapper;
    @Autowired
    private FeedMapper feedMapper;

    @Test
    public void postFeedWrite(){
        FileVO fileVO = new FileVO();
        fileVO.setFileName("test");
        fileVO.setFilePath("test");
        fileVO.setFileSize("125");
        fileMapper.postFeedWrite(fileVO);

        mapper.postFeedWrite(fileVO.getId(),50l);
    }


//    @Test
//    public void getFeedList(){
//
//        List<FeedListDTO> feedList = togetherFeedMapper.getFeedList();
//        feedList.forEach(feed -> {
//            feed.setFiles(mapper.getFileList(feed.getId()));
//            feed.setFeedTags(feedTagMapper.getFeedList(feed.getId()));
//        });
//        feedList.forEach(System.out::println);
//
//    }

    @Test
    public void getFeedModify(){
        Optional<FeedDTO> feedDTO = feedMapper.getFeedModify(48l);
        FeedDTO feed = feedDTO.orElseThrow(() -> new RuntimeException("FeedDTO not found"));
        feed.setFiles(mapper.getFileList(feed.getId()));
        log.info("feedDTO:{}",feedDTO);
    }
}
