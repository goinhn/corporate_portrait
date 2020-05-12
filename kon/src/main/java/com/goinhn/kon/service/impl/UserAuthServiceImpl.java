package com.goinhn.kon.service.impl;

import com.goinhn.kon.mapper.UserMapper;
import com.goinhn.kon.model.dto.TokenDTO;
import com.goinhn.kon.model.vo.LoginVO;
import com.goinhn.kon.model.entity.User;
import com.goinhn.kon.model.vo.RegisterVO;
import com.goinhn.kon.service.intf.MailService;
import com.goinhn.kon.service.intf.UserAuthService;
import com.goinhn.kon.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author goinhn
 */
@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUser(LoginVO loginVO) {
        if (loginVO == null) {
            return null;
        }
        String username = loginVO.getUsername();
        String pattern = "^[-_A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isMatch = Pattern.matches(pattern, username);

        User user = new User();
        if (isMatch) {
            user.setEmail(loginVO.getUsername());
            user.setPassword(loginVO.getPassword());
            return userMapper.selectByEmailAndPassword(user);
        } else {
            user.setUsername(loginVO.getUsername());
            user.setPassword(loginVO.getPassword());
            return userMapper.selectByUsernameAndPassword(user);
        }
    }


    private static final Integer EXPIRE = 6;


    @Override
    public TokenDTO createUserToken(User user) {
        if (user == null) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        LocalDateTime loginTime = LocalDateTime.now();
        LocalDateTime expireTime = loginTime.plusHours(EXPIRE);

        user.setToken(token);
        user.setLoginTime(loginTime);
        user.setExpireTime(expireTime);

        int count = userMapper.updateTokenLoginTimeExpireTimeById(user);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUsername(user.getUsername());
        tokenDTO.setToken(user.getToken());
        tokenDTO.setLoginTime(user.getLoginTime());
        tokenDTO.setExpireTime(user.getExpireTime());

        return count == 1 ? tokenDTO : null;
    }


    @Override
    public User findUserByToken(String token) {
        if (token == null) {
            return null;
        }
        User user = new User();
        user.setToken(token);
        return userMapper.selectByToken(user);
    }


    @Override
    public boolean userLoginOut(String token) {
        if (token == null) {
            return false;
        }
        User user = new User();
        user.setToken(token);

        user = userMapper.selectByToken(user);

        if (user == null) {
            return true;
        } else {
            String newToken = UUID.randomUUID().toString();
            user.setToken(newToken);
            int count = userMapper.updateTokenById(user);

            return count == 1;
        }
    }


    @Autowired
    private MailService mailService;


    @Override
    public Map<String, Object> userRegister(RegisterVO registerVO) {
        if (registerVO == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        User user = new User();
        user.setUsername(registerVO.getUsername());
        user.setEmail(registerVO.getEmail());
        user.setPassword(MD5Util.string2MD5(registerVO.getPassword()));
        user.setActiveStatus(0);
        String activeCode = UUID.randomUUID().toString().replace("-", "");
        user.setActiveCode(activeCode);

        if (userMapper.selectByUsername(user) != null) {
            map.put("flag", false);
            map.put("msg", "该用户名已经注册");
            return map;
        }
        if (userMapper.selectByEmail(user) != null) {
            map.put("flag", false);
            map.put("msg", "该邮箱已注册");
            return map;
        }

        String subject = "企业画像系统";
        String content = "<a href=\"http://portrait.cmyfwz.com:12140/kon/verify/useractive?activecode=" + activeCode + "\">激活账号点击：http://portrait.cmyfwz.com:12140/kon/verify/useractive?activecode=" + activeCode + "</a>";
        mailService.sendMimeMail(user.getEmail(), subject, content);

        int count = userMapper.saveUser(user);

        if (count == 1) {
            map.put("flag", true);
            map.put("msg", "注册成功，请查询邮箱进行激活");
        } else {
            map.put("flag", false);
            map.put("msg", "注册失败");
        }
        return map;
    }


    @Override
    public boolean userActive(String activeCode) {
        if (activeCode == null) {
            return false;
        }
        User user = new User();
        user.setActiveCode(activeCode);
        user = userMapper.selectByActiveCode(user);
        if (user == null) {
            return false;
        }
        user.setActiveStatus(1);
        int count = userMapper.updateActiveStatusById(user);

        return count == 1;
    }

    @Override
    public boolean userForgot(String username) {
        if (username == null) {
            return false;
        }
        String pattern = "^[-_A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isMatch = Pattern.matches(pattern, username);

        User user = new User();
        if (isMatch) {
            user.setEmail(username);
            user = userMapper.selectByEmail(user);
        } else {
            user.setUsername(username);
            user = userMapper.selectByUsername(user);
        }

        if (user == null) {
            return false;
        } else {
            String activeCode = UUID.randomUUID().toString().replace("-", "");
            user.setActiveCode(activeCode);
            String subject = "企业画像系统";
            String content = "<a href=\"http://portrait.cmyfwz.com:12140/kon/verify/userreset?activecode=" + activeCode + "\">重置密码点击：http://portrait.cmyfwz.com:12140/kon/verify/userreset?activecode=" + activeCode + "</a>";
            mailService.sendMimeMail(user.getEmail(), subject, content);

            int count = userMapper.updateActiveCodeById(user);

            return count == 1;
        }
    }

    @Override
    public boolean userReset(String activecode, String password) {
        if (activecode == null || password == null) {
            return false;
        }
        User user = new User();
        user.setActiveCode(activecode);
        user = userMapper.selectByActiveCode(user);
        if (user == null) {
            return false;
        }
        user.setPassword(password);
        int count = userMapper.updatePasswordById(user);

        return count == 1;
    }


}
