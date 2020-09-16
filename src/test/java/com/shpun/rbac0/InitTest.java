package com.shpun.rbac0;

import com.shpun.rbac0.mapper.*;
import com.shpun.rbac0.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: 数据初始化
 * @Author: sun
 * @Date: 2020/4/7 15:44
 */
@SpringBootTest
public class InitTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 添加用户：管理员，张三，李四
     */
    @Test
    public void insertUser() {
        User admin = new User();
        admin.setNickname("管理员");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        User zhangsan = new User();
        zhangsan.setNickname("张三");
        zhangsan.setUsername("zhangsan");
        zhangsan.setPassword(passwordEncoder.encode("zhangsan"));

        User lisi = new User();
        lisi.setNickname("李四");
        lisi.setUsername("lisi");
        lisi.setPassword(passwordEncoder.encode("lisi"));

        userMapper.insert(admin);
        userMapper.insert(zhangsan);
        userMapper.insert(lisi);
    }

    /**
     * 添加角色：管理员，测试员1，测试员2
     */
    @Test
    public void insertRole() {
        Role admin = new Role();
        admin.setRoleName("管理员");

        Role test1 = new Role();
        test1.setRoleName("测试员1");

        Role test2 = new Role();
        test2.setRoleName("测试员2");

        roleMapper.insert(admin);
        roleMapper.insert(test1);
        roleMapper.insert(test2);
    }

    /**
     * 添加菜单：page1~6
     */
    @Test
    public void insertMenu() {
        for (int i = 1;i <= 6; i++) {
            Menu menu = new Menu();
            menu.setMenuName("菜单page" + i);
            menu.setUrl("page/page" + i);
            menu.setPermission("PageController:page" + i);

            menuMapper.insert(menu);
        }
    }

    /**
     * 添加用户角色关联：
     * 管理员 - 管理员
     * 张三 - 测试员1
     * 李四 - 测试员2
     */
    @Test
    public void insertUserRole() {
        UserRole admin = new UserRole();
        admin.setUserId(1);
        admin.setRoleId(1);

        UserRole zhangsan = new UserRole();
        zhangsan.setUserId(2);
        zhangsan.setRoleId(2);

        UserRole lisi = new UserRole();
        lisi.setUserId(3);
        lisi.setRoleId(3);

        userRoleMapper.insert(admin);
        userRoleMapper.insert(zhangsan);
        userRoleMapper.insert(lisi);
    }

    /**
     * 添加角色菜单关联：
     * 管理员 page1~6
     * 测试员1 page1，page2
     * 测试员2 page1，page3
     */
    @Test
    public void insertRoleMenu() {
        for (int i = 1; i <= 6; i++) {
            RoleMenu admin = new RoleMenu();
            admin.setRoleId(1);
            admin.setMenuId(i);
            roleMenuMapper.insert(admin);

            if (i == 1 || i == 2) {
                RoleMenu test1 = new RoleMenu();
                test1.setRoleId(2);
                test1.setMenuId(i);
                roleMenuMapper.insert(test1);
            }
            if (i == 1 || i == 3) {
                RoleMenu test2 = new RoleMenu();
                test2.setRoleId(3);
                test2.setMenuId(i);
                roleMenuMapper.insert(test2);
            }
        }
    }

}
