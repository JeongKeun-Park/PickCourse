package com.app.pickcourse.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.pickcourse.domain.vo.WishVO;

@SpringBootTest
@Slf4j
public class WIshMapperTests {
    @Autowired
    private WishMapper wishMapper;

//    @Test
//    public void testInsert() {
//        WishVO wishVO = new WishVO();
//
//        wishVO.setMemberId(1L);
//        wishVO.setCourseId(1L);
//        wishMapper.insert(wishVO);
//
//    }
}
