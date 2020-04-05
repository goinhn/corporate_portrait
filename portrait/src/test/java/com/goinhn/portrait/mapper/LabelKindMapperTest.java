package com.goinhn.portrait.mapper;

import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.entity.LabelKind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class LabelKindMapperTest {

    @Autowired
    LabelKindMapper labelKindMapper;

    @Test
    public void testSelectAllByKindAndNumber() {
        LabelKind labelKind = LabelKind
                .builder()
                .kind("企业背景")
                .number(0)
                .build();
        labelKind = labelKindMapper.selectAllByKindAndNumber(labelKind);
        System.out.println(labelKind);
    }

}