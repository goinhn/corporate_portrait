package com.goinhn.kon.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息模型
 *
 * @author goinhn
 */
@Data
public class User {

    /**
     * 序号
     */
    private Long eid;

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

    /**
     * activeCode 用户激活码
     */
    private String activeCode;

    /**
     * activeStatus 用户激活状态，0：未激活，1：激活
     */
    private Integer activeStatus;


}
