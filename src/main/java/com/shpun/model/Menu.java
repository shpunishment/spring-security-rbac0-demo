package com.shpun.model;

/**
 * @Description: 菜单
 * @Author: sun
 * @Date: 2020/4/7 14:31
 */
public class Menu {

    private Integer id;

    private String menuName;

    private String url;

    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
