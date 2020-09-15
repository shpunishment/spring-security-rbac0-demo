package com.shpun.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: security 中 UserDetails 实现类
 * @Author: sun
 * @Date: 2020/4/7 14:39
 */
public class SecurityUserDetails implements UserDetails {

    private String username;

    private String password;

    private Integer enable;

    private List<GrantedAuthority> authorities;

    public SecurityUserDetails (String username, String password, Integer enable, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.enable = enable;

        // 权限值，在这里就是菜单的权限值
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (!authorities.isEmpty()) {
            for (String authority : authorities) {
                authorityList.add(new SimpleGrantedAuthority(authority));
            }
        }
        this.authorities = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable == 1;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return this.username;
    }
}
