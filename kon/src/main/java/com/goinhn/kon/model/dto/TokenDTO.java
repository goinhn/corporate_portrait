package com.goinhn.kon.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 传回前端的token模型
 *
 * @author goinhn
 */
@Data
public class TokenDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 信息
     */
    private String token;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * token过期时间
     */
    private LocalDateTime expireTime;

}

