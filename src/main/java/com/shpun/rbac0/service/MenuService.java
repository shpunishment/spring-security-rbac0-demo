package com.shpun.rbac0.service;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 15:21
 */
public interface MenuService {

    List<String> getPermissionByUserId (Integer userId);

}
