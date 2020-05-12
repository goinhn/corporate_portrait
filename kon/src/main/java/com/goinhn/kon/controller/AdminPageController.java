package com.goinhn.kon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员页面控制器
 *
 * @author goinhn
 */
@Api(tags = "管理员页面返回接口")
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminPageController {

    @ApiOperation(value = "返回管理员界面")
    @GetMapping(value = "/admin.html")
    public ModelAndView admin() {
        log.info("admin page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @ApiOperation(value = "返回用户管理界面")
    @GetMapping(value = "/people.html")
    public ModelAndView people() {
        log.info("people page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("people");
        return modelAndView;
    }

    @ApiOperation(value = "返回企业管理界面")
    @GetMapping(value = "/business.html")
    public ModelAndView business() {
        log.info("business page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("business");
        return modelAndView;
    }

}
