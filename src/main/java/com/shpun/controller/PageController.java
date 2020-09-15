package com.shpun.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 页面控制器
 * @Author: sun
 * @Date: 2020/4/7 15:28
 */
@Controller
public class PageController {

    @PreAuthorize("hasAuthority('PageController:page1')")
    @RequestMapping("/page1")
    public ModelAndView page1() {
        return new ModelAndView("page/page1");
    }

    @PreAuthorize("hasAuthority('PageController:page2')")
    @RequestMapping("/page2")
    public ModelAndView page2() {
        return new ModelAndView("page/page2");
    }

    @PreAuthorize("hasAuthority('PageController:page3')")
    @RequestMapping("/page3")
    public ModelAndView page3() {
        return new ModelAndView("page/page3");
    }

    @PreAuthorize("hasAuthority('PageController:page4')")
    @RequestMapping("/page4")
    public ModelAndView page4() {
        return new ModelAndView("page/page4");
    }

    @PreAuthorize("hasAuthority('PageController:page5')")
    @RequestMapping("/page5")
    public ModelAndView page5() {
        return new ModelAndView("page/page5");
    }

    @PreAuthorize("hasAuthority('PageController:page6')")
    @RequestMapping("/page6")
    public ModelAndView page6() {
        return new ModelAndView("page/page6");
    }

}
