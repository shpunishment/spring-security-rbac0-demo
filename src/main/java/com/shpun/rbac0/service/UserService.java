package com.shpun.rbac0.service;

import com.shpun.rbac0.model.User;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 14:46
 */
public interface UserService {

    User getByUsername (String username);

}
