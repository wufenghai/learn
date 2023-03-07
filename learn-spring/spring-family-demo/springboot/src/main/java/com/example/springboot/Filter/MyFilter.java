package com.example.springboot.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * 同样和servlet也是一个道理
 *
 * 通过注解方式，主要有两个注解，第一个为路径@WebFilter，第二个为扫描路径的注解@ServletComponentScan
 * 通过配置类注册组件
 *
 * @author wfh
 * @create 2023/2/28 18:40
 */
@WebFilter(urlPatterns = "/myfilter")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-------------------您已进入过滤器---------------------");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

