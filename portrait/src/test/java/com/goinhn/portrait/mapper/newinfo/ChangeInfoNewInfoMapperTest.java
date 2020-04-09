package com.goinhn.portrait.mapper.newinfo;

import com.goinhn.portrait.PortraitApplication;
import com.goinhn.portrait.model.entity.newinfo.ChangeInfoNewInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortraitApplication.class)
public class ChangeInfoNewInfoMapperTest {

    @Autowired
    ChangeInfoNewInfoMapper changeInfoNewInfoMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveChangeInfoNewInfo() {
        ChangeInfoNewInfo changeInfoNewInfo = new ChangeInfoNewInfo();
        changeInfoNewInfo.setEntName("begin");

        int result = changeInfoNewInfoMapper.saveChangeInfoNewInfo(changeInfoNewInfo);
        System.out.println(result);
        List<ChangeInfoNewInfo> changeInfoNewInfos = changeInfoNewInfoMapper.selectAllByEntName(changeInfoNewInfo);

        Iterator iterator = changeInfoNewInfos.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


}