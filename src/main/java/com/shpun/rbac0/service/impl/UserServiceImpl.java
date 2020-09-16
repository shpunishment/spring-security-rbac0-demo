package com.shpun.rbac0.service.impl;

import com.shpun.rbac0.mapper.UserMapper;
import com.shpun.rbac0.model.User;
import com.shpun.rbac0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 14:47
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
