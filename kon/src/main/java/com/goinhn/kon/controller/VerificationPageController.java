package com.goinhn.kon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录验证页面控制器
 *
 * @author goinhn
 */
@Api(tags = "登录验证页面返回接口")
@Slf4j
@RestController
@RequestMapping(value = "/verification")
public class VerificationPageController {

    @ApiOperation(value = "返回用户登录界面")
    @GetMapping(value = "/login.html")
    public ModelAndView login() {
        log.info("login page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @ApiOperation(value = "返回用户注册界面")
    @GetMapping(value = "/register.html")
    public ModelAndView register() {
        log.info("register page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @ApiOperation(value = "返回忘记密码界面")
    @GetMapping(value = "/forgot-password.html")
    public ModelAndView forgotPassword() {
        log.info("forgotPassword page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgot-password");
        return modelAndView;
    }

    @ApiOperation(value = "返回用户登录界面")
    @GetMapping(value = "/reset-password.html")
    public ModelAndView resetPassword() {
        log.info("resetPassword page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reset-password");
        return modelAndView;
    }

    @ApiOperation(value = "返回激活成功界面")
    @GetMapping(value = "/activesuccess.html")
    public ModelAndView activeSuccess() {
        log.info("activeSuccess page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activesuccess");
        return modelAndView;
    }

    @ApiOperation(value = "返回激活失败界面")
    @GetMapping(value = "/activefail.html")
    public ModelAndView activeFail() {
        log.info("activeFail page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activefail");
        return modelAndView;
    }

    @ApiOperation(value = "返回重置成功界面")
    @GetMapping(value = "/resetsuccess.html")
    public ModelAndView resetSuccess() {
        log.info("resetSuccess page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resetsuccess");
        return modelAndView;
    }

    @ApiOperation(value = "返回重置失败界面")
    @GetMapping(value = "/resetfail.html")
    public ModelAndView resetFail() {
        log.info("resetFail page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resetfail");
        return modelAndView;
    }

}
