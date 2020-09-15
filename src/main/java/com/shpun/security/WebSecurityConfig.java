package com.shpun.security;

import com.shpun.handler.LoginFailureHandler;
import com.shpun.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: sun
 * @Date: 2020/4/7 15:07
 */
@Configuration
@EnableWebSecurity
// 开启方法级别保护
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private DataSource dataSource;

    private static final int REMEMBER_ME_TIMEOUT = 7 * 24 * 60 * 60;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // / 和 /home 路径配置为不需要任何身份验证，其他所有路径必须经过验证
                .antMatchers("/", "/home", "/open/*").permitAll()
                // 其他请求都需要已认证
                .anyRequest().authenticated()
                .and()
                // 使用表单登录
                .formLogin()
                // 自定义username 和password参数
                .usernameParameter("login_username")
                .passwordParameter("login_password")
                // 自定义登录页地址
                .loginPage("/loginPage")
                // 验证表单的地址，由过滤器 UsernamePasswordAuthenticationFilter 拦截处理
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
                // 记住我
                .rememberMe()
                // 自定义remember-me参数
                .rememberMeParameter("login_remember_me")
                // 配置从数据库persistent_logins中读取
                .tokenRepository(persistentTokenRepository())
                // rememberMe 的有效时间
                .tokenValiditySeconds(100)
                .userDetailsService(userDetailsService)
                .and()
                // 默认为 /logout ，登出后默认跳转到 /login?logout ,上面修改了登录页地址后回跳到 /loginPage?logout
                .logout()
                // 无效会话
                .invalidateHttpSession(true)
                // 清除身份验证
                .clearAuthentication(true)
                // 删除cookie
                .deleteCookies()
                .permitAll()
                .and()
                // 权限不足跳转 /401
                .exceptionHandling().accessDeniedPage("/401")
                .and()
                .csrf().disable();

        // session 管理
        http.sessionManagement()
                .maximumSessions(1)
                // 当达到最大值时，是否保留已经登录的用户为true，新用户无法登录，可到登陆失败handler处理；为 false，旧用户被踢出，被CustomExpiredStrategy处理
                .maxSessionsPreventsLogin(false)
                // 当达到最大值时，旧用户被踢出后的操作
                .expiredSessionStrategy(new CustomExpiredStrategy());
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // true 会自动创建persistent_logins表，若已创建，则注释掉否则会报错。
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
