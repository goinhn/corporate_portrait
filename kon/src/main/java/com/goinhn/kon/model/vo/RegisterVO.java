package com.goinhn.kon.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 前端传回的注册模型
 *
 * @author goinhn
 */
@ApiModel("注册模型")
@Data
public class RegisterVO {

    /**
     * username 用户名称
     */
    private String username;

    /**
     * email 邮箱
     */
    private String email;

    /**
     * password 密码
     */
    private String password;

}
