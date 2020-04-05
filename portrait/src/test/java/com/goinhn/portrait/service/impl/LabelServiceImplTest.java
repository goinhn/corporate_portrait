package com.goinhn.portrait.service.impl;

import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.dto.ShowLabel;
import com.goinhn.portrait.service.intf.LabelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class LabelServiceImplTest {

    @Autowired
    LabelService labelService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetLabelValue() {
        ShowLabel showlabel = labelService.getLabelValue("ebc9e192ba17fd128b1b24ce89dd62f9");
        System.out.println(showlabel);
    }
}