package com.goinhn.portrait.dao;


import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.BusinessBackgroundValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class BusinessBackgroundValueMapperTest {

    @Autowired
    private BusinessBackgroundValueMapper businessBackgroundValueMapper;

    @Test
    public void testSelectAllByEntName() {
        BusinessBackgroundValue businessBackgroundValue = new BusinessBackgroundValue();
        businessBackgroundValue.setEntName("d7a53be04656e8aca9751afa7c43e7cf");
        businessBackgroundValue = businessBackgroundValueMapper.selectAllByEntName(businessBackgroundValue);
        System.out.println(businessBackgroundValue);
    }
}
