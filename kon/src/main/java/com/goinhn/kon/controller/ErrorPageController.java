package com.goinhn.kon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 错误控制器
 *
 * @author goinhn
 */
@Api(tags = "错误返回接口")
@Slf4j
@RestController
@RequestMapping(value = "/error")
public class ErrorPageController {

    @ApiOperation(value = "返回404")
    @GetMapping(value = "/404.html")
    public ModelAndView error404() {
        log.info("404 page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ApiOperation(value = "返回500")
    @GetMapping(value = "/500.html")
    public ModelAndView error500() {
        log.info("500 page\n");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("500");
        return modelAndView;
    }
}
