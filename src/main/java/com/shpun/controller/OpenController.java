package com.shpun.controller;

import com.shpun.security.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/9/15 10:39
 */
@RequestMapping("/open")
@RestController
public class OpenController {

    @RequestMapping("/getCurrentUser")
    public SecurityUserDetails getCurrentUser() {
        SecurityUserDetails securityUserDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getDetails();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String) {
            // 未登录为anonymousUser
            if ("anonymousUser".equals(principal)) {
                return securityUserDetails;
            }
        } else {
            securityUserDetails = (SecurityUserDetails) principal;
        }
        return securityUserDetails;
    }

}
