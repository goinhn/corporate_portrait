package com.goinhn.portrait.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 错误控制器
 *
 * @author goinhn
 */
@Api(tags = "错误返回接口")
@RestController
@RequestMapping(value = "/error")
public class ErrorController {

    @ApiOperation(value = "返回404")
    @GetMapping(value = "/{errorNumber}")
    public ModelAndView index(@PathVariable("errorNumber") Integer errorNumber) {
        ModelAndView modelAndView = new ModelAndView();
        if (errorNumber == 404) {
            modelAndView.setViewName("404");
        }
        modelAndView.setViewName("404");
        return modelAndView;
    }

}
