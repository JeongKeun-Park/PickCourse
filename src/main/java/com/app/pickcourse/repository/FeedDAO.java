package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;

import com.app.pickcourse.domain.vo.FeedVO;

import com.app.pickcourse.domain.dto.FeedListDTO;

import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDAO {
    private final FeedMapper feedMapper;

//    추가
    public void save(FeedVO feedVO) {
        feedMapper.postFeedWrite(feedVO);
    }

    public ReplyDetailDTO getReportDetail(Long id) {
        return feedMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }

    public void postFeedWrite(FeedDTO feedDTO) {
        feedMapper.postFeedWrite(feedDTO);
    }

    public void postRealWrite(RealDTO realDTO) {
        feedMapper.postRealWrite(realDTO);
    }

    public void postFeedModify(FeedVO feedVO) {
        feedMapper.PostFeedModify(feedVO);
    }

    public void deleteFeedModify(Long id) {
        feedMapper.deleteFeedModify(id);
    }

    public List<FeedListDTO> getFeedList() {
        return feedMapper.getFeedList();
    }

    public List<FeedListDTO> getMyFeedList(long memberId) {
        return feedMapper.getMyFeedList(memberId);
    }
}
