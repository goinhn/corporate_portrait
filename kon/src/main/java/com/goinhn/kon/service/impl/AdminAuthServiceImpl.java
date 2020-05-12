package com.goinhn.kon.service.impl;

import com.goinhn.kon.mapper.AdminMapper;
import com.goinhn.kon.model.dto.TokenDTO;
import com.goinhn.kon.model.entity.Admin;
import com.goinhn.kon.model.vo.LoginVO;
import com.goinhn.kon.service.intf.AdminAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author goinhn
 */
@Slf4j
@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin findAdmin(LoginVO loginVO) {
        String username = loginVO.getUsername();
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        boolean isMatch = Pattern.matches(pattern, username);

        Admin admin = new Admin();
        if (isMatch) {
            admin.setEmail(loginVO.getUsername());
            admin.setPassword(loginVO.getPassword());
            return adminMapper.selectByEmailAndPassword(admin);
        } else {
            admin.setUsername(loginVO.getUsername());
            admin.setPassword(loginVO.getPassword());
            return adminMapper.selectByUsernameAndPassword(admin);
        }
    }


    private static final Integer EXPIRE = 6;


    @Override
    public TokenDTO createAdminToken(Admin admin) {
        String token = UUID.randomUUID().toString();
        LocalDateTime loginTime = LocalDateTime.now();
        LocalDateTime expireTime = loginTime.plusHours(EXPIRE);

        admin.setToken(token);
        admin.setLoginTime(loginTime);
        admin.setExpireTime(expireTime);

        int count = adminMapper.updateTokenLoginTimeExpireTimeById(admin);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUsername(admin.getUsername());
        tokenDTO.setToken(admin.getToken());
        tokenDTO.setLoginTime(admin.getLoginTime());
        tokenDTO.setExpireTime(admin.getExpireTime());

        return count == 1 ? tokenDTO : null;
    }


    @Override
    public Admin findAdminByToken(String token) {
        Admin admin = new Admin();
        admin.setToken(token);

        return adminMapper.selectByToken(admin);
    }


    @Override
    public boolean adminLoginOut(String token) {
        Admin admin = new Admin();
        admin.setToken(token);

        admin = adminMapper.selectByToken(admin);

        if (admin == null) {
            return true;
        } else {
            String newToken = UUID.randomUUID().toString();
            admin.setToken(newToken);
            int count = adminMapper.updateTokenById(admin);

            return count == 1;
        }
    }


}
