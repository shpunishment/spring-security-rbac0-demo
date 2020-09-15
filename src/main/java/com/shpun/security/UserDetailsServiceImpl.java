package com.shpun.security;

import com.shpun.model.User;
import com.shpun.service.MenuService;
import com.shpun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserDetailsService 接口实现类
 * @Author: sun
 * @Date: 2020/4/7 14:45
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByUsername(s);
        if (user != null) {
            List<String> authorities = menuService.getPermissionByUserId(user.getId());
            return new SecurityUserDetails(user.getUsername(), user.getPassword(), user.getEnable(), authorities);
        }
        return null;
    }
}
