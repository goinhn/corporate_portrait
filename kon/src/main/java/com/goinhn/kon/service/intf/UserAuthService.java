package com.goinhn.kon.service.intf;

import com.goinhn.kon.model.dto.TokenDTO;
import com.goinhn.kon.model.vo.LoginVO;
import com.goinhn.kon.model.entity.User;
import com.goinhn.kon.model.vo.RegisterVO;

import java.util.Map;

/**
 * @author goinhn
 */
public interface UserAuthService {

    /**
     * 验证用户登录是否成功
     *
     * @param loginVO 登录模型
     * @return
     */
    User findUser(LoginVO loginVO);


    /**
     * 生成用户的token
     *
     * @param user 用户
     * @return
     */
    TokenDTO createUserToken(User user);


    /**
     * 根据token信息来查询用户
     *
     * @param token token信息
     * @return
     */
    User findUserByToken(String token);


    /**
     * 根据token来注销用户
     *
     * @param token
     * @return
     */
    boolean userLoginOut(String token);


    /**
     * 用户注册
     *
     * @param registerVO 用户注册模型
     * @return
     */
    Map<String, Object> userRegister(RegisterVO registerVO);


    /**
     * 激活用户
     *
     * @param activeCode 激活码
     * @return
     */
    boolean userActive(String activeCode);


    /**
     * 重置密码
     *
     * @param username 用户名
     * @return
     */
    boolean userForgot(String username);


    /**
     * 重置密码
     *
     * @param activecode
     * @param password
     * @return
     */
    boolean userReset(String activecode, String password);
}
