package com.goinhn.portrait.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分析数值模块请求接收
 *
 * @author goinhn
 */
@Api(tags = "首页返回接口")
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @ApiOperation(value = "返回首页")
    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
