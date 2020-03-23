package com.goinhn.portrait.mapper;


import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.entity.BusinessBackgroundAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class BusinessBackgroundAnalysisMapperTest {

    @Autowired
    private BusinessBackgroundAnalysisMapper businessBackgroundAnalysisMapper;

    @Test
    public void testSelectAllByEntName() {
        BusinessBackgroundAnalysis businessBackgroundAnalysis = new BusinessBackgroundAnalysis();
        businessBackgroundAnalysis.setEntName("d7a53be04656e8aca9751afa7c43e7cf");
        businessBackgroundAnalysis = businessBackgroundAnalysisMapper.selectAllByEntName(businessBackgroundAnalysis);
        System.out.println(businessBackgroundAnalysis);
    }
}
