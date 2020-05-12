package com.goinhn.kon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户页面控制器
 *
 * @author goinhn
 */
@Api(tags = "用户页面返回接口")
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserPageController {

    @ApiOperation(value = "返回首页界面")
    @GetMapping(value = "/user.html")
    public ModelAndView user() {
        log.info("user page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @ApiOperation(value = "返回企业搜索界面")
    @GetMapping(value = "/search.html")
    public ModelAndView search() {
        log.info("search page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @ApiOperation(value = "返回新企业分类界面")
    @GetMapping(value = "/new-class.html")
    public ModelAndView newClass() {
        log.info("new-class page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-class");
        return modelAndView;
    }

}
