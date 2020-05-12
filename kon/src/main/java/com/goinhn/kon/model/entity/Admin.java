package com.goinhn.kon.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员信息模型
 *
 * @author goinhn
 */
@Data
public class Admin {

    /**
     * 序号
     */
    private Integer eid;

    /**
     * username 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * password 密码
     */
    private String password;

    /**
     * token 登陆凭证
     */
    private String token;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * token 过期时间
     */
    private LocalDateTime expireTime;


}
