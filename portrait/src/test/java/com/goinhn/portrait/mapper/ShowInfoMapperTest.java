package com.goinhn.portrait.mapper;

import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.dto.Page;
import com.goinhn.portrait.model.entity.ShowInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class ShowInfoMapperTest {

    @Autowired
    ShowInfoMapper showInfoMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSelectAllByEntName() {
        ShowInfo showInfo = ShowInfo
                .builder()
                .entName("d7a53be04656e8aca9751afa7c43e7cf")
                .build();
        showInfo = showInfoMapper.selectAllByEntName(showInfo);
        System.out.println(showInfo);
    }

    @Test
    public void testSelectAllLikeEntName() {
        Page<String> page = Page
                .<String>builder()
                .entName("a")
                .pageIndex(10)
                .pageSize(100)
                .build();
        List<ShowInfo> showInfos = showInfoMapper.selectAllLikeEntName(page);

        for (ShowInfo showInfo : showInfos) {
            System.out.println(showInfo);
        }
    }


    @Test
    public void testFindAllCount() {
        int result = showInfoMapper.findAllCount();
        System.out.println(result);
    }

}