package com.goinhn.kon.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 接收前端传回的登录数据
 *
 * @author goinhn
 */
@ApiModel("登录模型")
@Data
public class LoginVO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

}
