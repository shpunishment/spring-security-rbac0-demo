# spring-security-rbac0-demo
spring-security-rbac0-demo是使用Spring Security对RBAC(Role-Based Access Control，基于角色的访问控制)中的RBAC0的实现。

RBAC0 定义了能构成 RBAC 权限控制系统的最小的集合，RBAC0 由四部分构成：
1. 用户（User）：权限的使用主体
2. 角色（Role）：包含许可的集合
3. 会话（Session）：绑定用户和角色关系映射的中间通道。而且用户必须通过会话才能给用户设置角色。
4. 许可（Pemission）：对特定资源的特定的访问许可。

根据以上分析，需要用户表，角色表，菜单表，用户角色关联表以及角色菜单关联表：

| 表                       | 字段                                     |
| :----------------------- | :--------------------------------------- |
| 用户表 user              | id，nickname，username，password，enable |
| 角色表 role              | id，role_name                            |
| 菜单表 menu              | id，menu_name，url，permission           |
| 用户角色关联表 user_role | id，user_id，role_id                     |
| 角色菜单关联表 role_menu | id，role_id，menu_id                     |

**关系**

菜单有权限值，通过角色来分配菜单，再把角色分配给用户。

用户所属不同的角色，或修改所属角色拥有的菜单，从而实现对用户权限的控制。

**实现**

/ 和 /home 无权限可访问，对page1~page6这六个页面进行角色权限控制。

**测试**
1. 添加用户：管理员，张三，李四
2. 添加角色：管理员，测试员1，测试员2
3. 添加菜单：page1~6
4. 添加用户角色关联：管理员 - 管理员，张三 - 测试员1，李四 - 测试员2
5. 添加角色菜单关联：管理员（page1~6），测试员1（page1，page2），测试员2（page1，page3）
