package com.shpun.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 重写登录失败的handler，可对其进行扩展，比如添加登录日志等
 * @Author: sun
 * @Date: 2020/9/15 10:39
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // todo 记录登录日志等

        String errorMsg = "";
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorMsg = "账号密码错误!";
        } else if (exception instanceof LockedException) {
            errorMsg = "该账号被锁定!";
        } else if (exception instanceof AccountExpiredException) {
            errorMsg = "账户已过期!";
        } else if (exception instanceof DisabledException) {
            errorMsg = "该账户不可用!";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMsg = "账户异常，请联系管理员!";
        } else {
            errorMsg = "系统异常!";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", errorMsg);
        map.put("data", exception.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

}
