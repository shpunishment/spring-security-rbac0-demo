package com.shpun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 登录控制器
 * @Author: sun
 * @Date: 2020/4/9 16:17
 */
@Controller
public class LoginController {

    @RequestMapping(path = {"/", "/home"})
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage");
    }

    @RequestMapping("/401")
    public ModelAndView error401() {
        return new ModelAndView("error/401");
    }

}
