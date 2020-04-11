package com.goinhn.portrait.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页控制器
 *
 * @author goinhn
 */
@Api(tags = "首页返回接口")
@Slf4j
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @ApiOperation(value = "返回首页")
    @GetMapping(value = "/")
    public ModelAndView index() {
        log.info("index page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
