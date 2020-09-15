package com.shpun.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 重写登录成功的handler，可对其进行扩展，比如添加登录日志等。默认会在登录成功后，重定向到之前的地址
 * @Author: sun
 * @Date: 2020/9/15 10:39
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${server.servlet.context-path:'/'}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // todo 记录登录日志等

        // 获取原先的跳转页面
        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest != null) {
            redirectUrl = savedRequest.getRedirectUrl();
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "登录成功！");
        map.put("data", redirectUrl);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(map));
    }
}
