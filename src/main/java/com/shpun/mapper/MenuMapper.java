package com.shpun.mapper;

import com.shpun.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 14:34
 */
public interface MenuMapper {

    List<String> getPermissionByUserId (@Param("userId") Integer userId);

    void insert(Menu menu);

}
