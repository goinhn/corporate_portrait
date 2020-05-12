package com.goinhn.kon.interceptor;

import com.goinhn.kon.model.entity.Admin;
import com.goinhn.kon.model.entity.User;
import com.goinhn.kon.model.vo.ResultInfo;
import com.goinhn.kon.service.intf.AdminAuthService;
import com.goinhn.kon.service.intf.UserAuthService;
import com.goinhn.kon.utils.HttpContextUtil;
import com.goinhn.kon.utils.JsonChange;
import com.goinhn.kon.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author goinhn
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AdminAuthService adminAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = TokenUtil.getRequestToken(request);
        if (StringUtils.isBlank(token)) {
            response.sendRedirect("/kon/verification/login.html");
            return false;
        }

        User user = userAuthService.findUserByToken(token);
        Admin admin = adminAuthService.findAdminByToken(token);


        if (user == null && admin == null) {
            response.sendRedirect("/kon/verification/login.html");
            return false;
        }

        if (user != null) {
            if (user.getExpireTime().isBefore(LocalDateTime.now())) {
                response.sendRedirect("/kon/verification/login.html");
                return false;
            }
        }

        if (admin != null) {
            if (admin.getExpireTime().isBefore(LocalDateTime.now())) {
                response.sendRedirect("/kon/verification/login.html");
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private static void setReturn(HttpServletResponse response, boolean flag, int status, String data, String errorMessages) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(status);
        response.setContentType("application/json;charset=utf-8");

        ResultInfo resultInfo = ResultInfo
                .builder()
                .flag(flag)
                .status(status)
                .data(data)
                .errorMsg(errorMessages)
                .build();

        String json = JsonChange.objectToJson(resultInfo);
        httpResponse.getWriter().print(json);
    }


}
