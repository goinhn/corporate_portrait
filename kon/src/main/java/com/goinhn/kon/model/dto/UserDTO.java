package com.goinhn.kon.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 传回前端的用户信息模型
 *
 * @author goinhn
 */
@Data
public class UserDTO {

    /**
     * eid 序号
     */
    private Long eid;

    /**
     * username 用户名
     */
    private String username;

    /**
     * email 邮箱
     */
    private String email;

    /**
     * loginTime 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * activeTime 激活状态
     */
    private String activeStatus;

}
