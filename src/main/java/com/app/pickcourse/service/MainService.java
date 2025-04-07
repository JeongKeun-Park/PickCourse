package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MainDTO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MainService {
    private final MainDAO mainDAO;
    private final TagDAO tagDAO;
    private final GeneralFileDAO generalFileDAO;

    public MainDTO getCourses() {
        MainDTO mainDTO = new MainDTO();
        mainDTO.setCourses(mainDAO.findByCourseType());
        mainDTO.setFeeds(mainDAO.findFeeds());
        mainDTO.setVolunteer(mainDAO.findVolunteer());

        mainDTO.getFeeds().forEach(feed -> {
            feed.setTags(tagDAO.getFeedModify(feed.getId()));
            feed.setFiles(generalFileDAO.getFeedModify(feed.getId()));
            feed.setMember(mainDAO.selectById(feed.getMemberId()));
        });

        mainDTO.getCourses().forEach(course -> {
            course.setCount(Optional.ofNullable(mainDAO.findCount(course.getCourseType())).orElse(0));
        });

        return mainDTO;
    }
}
