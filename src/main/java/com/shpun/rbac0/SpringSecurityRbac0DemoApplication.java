package com.shpun.rbac0;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shpun.rbac0.mapper")
public class SpringSecurityRbac0DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityRbac0DemoApplication.class, args);
    }

}
