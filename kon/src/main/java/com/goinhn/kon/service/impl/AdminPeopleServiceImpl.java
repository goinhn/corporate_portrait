package com.goinhn.kon.service.impl;

import com.goinhn.kon.mapper.UserMapper;
import com.goinhn.kon.model.dto.Page;
import com.goinhn.kon.model.dto.UserDTO;
import com.goinhn.kon.model.entity.User;
import com.goinhn.kon.service.intf.AdminPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminPeopleServiceImpl implements AdminPeopleService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int countLikeUser(String search, int pageIndex, int pageSize) {
        Page page = new Page();
        page.setName(search);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        return userMapper.selectLikeCount(page);
    }


    @Override
    public int countUser() {
        return userMapper.selectCount();
    }


    @Override
    public List<UserDTO> filterUser(String search, int pageIndex, int pageSize) {
        Page page = new Page();
        page.setName(search);
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        List<User> users = userMapper.selectLikeUsername(page);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDTO userDTO = new UserDTO();

            userDTO.setEid(user.getEid());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setActiveStatus(user.getActiveStatus() != null ? user.getActiveStatus() == 1 ? "已激活" : "未激活" : "未激活");
            userDTO.setLoginTime(user.getLoginTime());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(Long id) {
        User user = new User();
        user.setEid(id);
        userMapper.deleteUserById(user);

        return true;
    }
}
