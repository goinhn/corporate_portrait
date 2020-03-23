package com.goinhn.portrait.mapper;

import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.vo.ShowInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class ShowInfoMapperTest {

    @Autowired
    ShowInfoMapper showInfoMapper;

    @Test
    public void testSelectByEntName() {
        ShowInfo showInfo = new ShowInfo();
        showInfo.setEntName("d7a53be04656e8aca9751afa7c43e7cf");
        showInfo = showInfoMapper.selectByEntName(showInfo);
        System.out.println(showInfo);
    }
}
