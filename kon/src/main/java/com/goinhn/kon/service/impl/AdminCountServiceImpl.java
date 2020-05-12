package com.goinhn.kon.service.impl;

import com.goinhn.kon.mapper.AdminMapper;
import com.goinhn.kon.mapper.ShowInfoMapper;
import com.goinhn.kon.mapper.UserMapper;
import com.goinhn.kon.service.intf.AdminCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCountServiceImpl implements AdminCountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ShowInfoMapper showInfoMapper;


    @Override
    public int countUser() {
        return userMapper.selectCount();
    }

    @Override
    public int countAdmin() {
        return adminMapper.selectCount();
    }

    @Override
    public int countBusiness() {
        return showInfoMapper.selectCount();
    }
}
