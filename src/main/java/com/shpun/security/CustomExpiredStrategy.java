package com.shpun.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Description: 用户被踢出时的操作，可以返回信息或者直接重定向到登录页
 * @Author: sun
 * @Date: 2020/4/16 9:19
 */
public class CustomExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//        Map<String, Object> map = new HashMap<>(2);
//        map.put("code", -1);
//        map.put("msg", "您已在另一台机器上登录。" );
//
//        event.getResponse().setContentType("application/json;charset=UTF-8");
//        event.getResponse().getWriter().write(JSON.toJSONString(map));

        // 重定向到登录页
        event.getResponse().sendRedirect("./loginPage");
    }
}
