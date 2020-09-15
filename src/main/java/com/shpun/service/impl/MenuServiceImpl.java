package com.shpun.service.impl;

import com.shpun.mapper.MenuMapper;
import com.shpun.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 15:21
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> getPermissionByUserId(Integer userId) {
        return menuMapper.getPermissionByUserId(userId);
    }
}
