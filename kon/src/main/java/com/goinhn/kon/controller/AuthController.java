package com.goinhn.kon.controller;


import com.goinhn.kon.model.entity.Admin;
import com.goinhn.kon.model.vo.LoginVO;
import com.goinhn.kon.model.entity.User;
import com.goinhn.kon.model.vo.RegisterVO;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.model.dto.TokenDTO;
import com.goinhn.kon.service.intf.*;
import com.goinhn.kon.utils.MD5Util;
import com.goinhn.kon.utils.ResultInfoUtil;
import com.goinhn.kon.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;


/**
 * @author goinhn
 */
@Api(tags = "验证接口")
@RestController()
@RequestMapping(value = "/verify")
@CrossOrigin
@Slf4j
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AdminAuthService adminAuthService;


    /**
     * @param loginVO 登录模型
     * @return
     */
    @ApiOperation(value = "用户登录接口")
    @PostMapping(value = "/userlogin")
    public ResultInfo userLogin(@RequestBody
                                @NotNull LoginVO loginVO) {
        log.info("/kon/verify/userlogin----------{}\n", loginVO);

        String password = loginVO.getPassword();
        loginVO.setPassword(MD5Util.string2MD5(password));

        TokenDTO tokenDTO;
        User user = userAuthService.findUser(loginVO);

        if (user == null) {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "验证失败",
                    "/kon/verify/userlogin");
        }
        if (user.getActiveStatus() == 0) {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "请先激活账号",
                    "/kon/verify/userlogin");
        }
        //生成token返回
        tokenDTO = userAuthService.createUserToken(user);
        if (tokenDTO == null) {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "验证失败",
                    "/kon/verify/userlogin");
        }

        return ResultInfoUtil.createResultInfo(
                true,
                200,
                tokenDTO,
                "",
                "/kon/verify/userlogin");
    }


    /**
     * @param loginVO 登录模型
     * @return
     */
    @ApiOperation(value = "管理员登录接口")
    @PostMapping(value = "/adminlogin")
    public ResultInfo adminLogin(@RequestBody
                                 @NotNull LoginVO loginVO) {
        log.info("/kon/verify/adminlogin----------{}\n", loginVO);

        String password = loginVO.getPassword();
        loginVO.setPassword(MD5Util.string2MD5(password));

        TokenDTO tokenDTO;
        Admin admin = adminAuthService.findAdmin(loginVO);
        if (admin == null) {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "验证失败",
                    "/kon/verify/adminlogin");
        }
        //生成token返回
        tokenDTO = adminAuthService.createAdminToken(admin);
        if (tokenDTO == null) {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    "验证失败",
                    "/kon/verify/adminlogin");
        }

        return ResultInfoUtil.createResultInfo(
                true,
                200,
                tokenDTO,
                "",
                "/kon/verify/adminlogin");
    }


    /**
     * @param
     * @return
     */
    @ApiOperation(value = "用户注销接口")
    @GetMapping(value = "/userloginout")
    public ResultInfo userLoginOut(HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        log.info("/kon/verify/userloginout----------{}\n", token);

        boolean result = userAuthService.userLoginOut(token);

        return ResultInfoUtil.createResultInfo(
                result,
                200,
                null,
                "",
                "/kon/verify/userloginout");
    }


    /**
     * @param
     * @return
     */
    @ApiOperation(value = "管理员注销接口")
    @GetMapping(value = "/adminloginout")
    public ResultInfo adminLoginOut(HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        log.info("/kon/verify/adminloginout----------{}\n", token);

        boolean result = adminAuthService.adminLoginOut(token);

        return ResultInfoUtil.createResultInfo(
                result,
                200,
                null,
                "",
                "/kon/verify/userloginout");
    }


    /**
     * @param registerVO
     * @return
     */
    @ApiOperation(value = "用户注册接口")
    @PostMapping(value = "/userregister")
    public ResultInfo userRegister(@RequestBody
                                   @NotNull RegisterVO registerVO) {
        log.info("/kon/verify/userregister----------{}\n", registerVO.toString());

        Map<String, Object> map = userAuthService.userRegister(registerVO);

        if ((Boolean) map.get("flag")) {
            return ResultInfoUtil.createResultInfo(
                    true,
                    200,
                    map.get("msg"),
                    "",
                    "/kon/verify/userregister");
        } else {
            return ResultInfoUtil.createResultInfo(
                    false,
                    200,
                    "",
                    (String) map.get("msg"),
                    "/kon/verify/adminlogin");
        }
    }


    /**
     * @param request
     * @return
     */
    @ApiOperation(value = "用户激活接口")
    @GetMapping(value = "/useractive")
    public ModelAndView userActive(HttpServletRequest request) {
        String activeCode = request.getParameter("activecode");
        log.info("/kon/verify/useractive----------{}\n", activeCode);
        ModelAndView modelAndView = new ModelAndView();

        if (activeCode == null || activeCode.trim().length() == 0) {
            modelAndView.setViewName("activefail");
            return modelAndView;
        }

        boolean result = userAuthService.userActive(activeCode);
        if (result) {
            modelAndView.setViewName("activesuccess");
            return modelAndView;
        } else {
            modelAndView.setViewName("activefail");
            return modelAndView;
        }
    }


    /**
     * @param request
     * @return
     */
    @ApiOperation(value = "用户忘记密码接口")
    @GetMapping(value = "/userforgot")
    public ResultInfo userForgot(HttpServletRequest request) {
        String username = request.getParameter("username");
        log.info("/kon/verify/userforgot----------{}\n", username);

        boolean result = userAuthService.userForgot(username);

        return ResultInfoUtil.createResultInfo(
                result,
                200,
                "请查询邮件进行重置",
                "该用户不存在",
                "/kon/verify/userforgot");
    }


    /**
     * @param request
     * @return
     */
    @ApiOperation(value = "用户激活重置密码接口")
    @GetMapping(value = "/userreset")
    public ModelAndView userReset(HttpServletRequest request) {
        String activecode = request.getParameter("activecode");
        log.info("/kon/verify/userreset----------{}\n", activecode);

        boolean result = userAuthService.userActive(activecode);
        ModelAndView modelAndView = new ModelAndView();

        if (result) {
            modelAndView.setViewName("resetsuccess");
            modelAndView.addObject("activecode", activecode);
            return modelAndView;
        } else {
            modelAndView.setViewName("resetfail");
            return modelAndView;
        }
    }


    /**
     * @param request
     * @return
     */
    @ApiOperation(value = "用户重置设置密码接口")
    @GetMapping(value = "/userresetpassword")
    public ResultInfo userResetPassword(HttpServletRequest request) {
        String activecode = request.getParameter("activecode");
        String password = request.getParameter("password");
        password = MD5Util.string2MD5(password);
        log.info("/kon/verify/userresetpassword----------activecode:{} password:{}\n", activecode, password);

        boolean result = userAuthService.userReset(activecode, password);

        return ResultInfoUtil.createResultInfo(
                result,
                200,
                "重置成功",
                "重置失败",
                "/kon/verify/userresetpassword");
    }
}
















