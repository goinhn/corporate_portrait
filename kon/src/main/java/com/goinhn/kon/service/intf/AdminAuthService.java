package com.goinhn.kon.service.intf;

import com.goinhn.kon.model.dto.TokenDTO;
import com.goinhn.kon.model.entity.Admin;
import com.goinhn.kon.model.vo.LoginVO;

/**
 * @author goinhn
 */
public interface AdminAuthService {

    /**
     * 验证管理员登录是否成功
     *
     * @param loginVO 登录模型
     * @return
     */
    Admin findAdmin(LoginVO loginVO);


    /**
     * 生成管理员的token
     *
     * @param admin 管理员
     * @return
     */
    TokenDTO createAdminToken(Admin admin);


    /**
     * 根据token信息来查询管理员
     *
     * @param token token信息
     * @return
     */
    Admin findAdminByToken(String token);


    /**
     * 根据token信息来注销用户
     *
     * @param token token信息
     * @return
     */
    boolean adminLoginOut(String token);

}
