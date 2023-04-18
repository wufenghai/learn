package com.example.lab01.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author wfh
 * @create 2023/4/18 9:20
 */
@Configuration
@EnableWebSecurity
//开启对 Spring Security 注解的方法，进行权限验证。
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    /**
     * 设置http请求路径
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/test/demo").permitAll() // 所有用户可访问
                .antMatchers("/test/admin").hasRole("ADMIN") // 需要 ADMIN 角色
                .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
                // 任何请求，访问的用户都需要经过认证
                .anyRequest().authenticated()//所有请求，都需要登录状态校验
                .and()
                // 设置 Form 表单登陆
                .formLogin()
//                .loginPage("/login") // 登陆 URL 地址
                .permitAll() // 所有用户可访问;
                .and()
                // 配置退出相关
                .logout()
//                    .logoutUrl("/logout") // 退出 URL 地址
                .permitAll(); // 所有用户可访问
        return httpSecurity.build();
    }

    /**
     * 设置内置用户
     *
     * @return
     */
    @Bean
    public UserDetailsService users() {
        // 不使用 PasswordEncoder 密码编码器
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails admin = users
                .username("normal")
                .password("normal")
                .roles("NORMAL")
                .build();
        // 使用内存中的 InMemoryUserDetailsManager
        return new InMemoryUserDetailsManager(user, admin);
    }
}

