package com.example.springboot.config;

import com.example.springboot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 最主要的配置类，相当于xml的配置文件
 * 继承WebMvcConfigurer改写addInterceptors这个方法，实现什么类拦截什么类不拦截
 * addInterceptor()括号中书写用于拦截哪个类
 *
 * @author wfh
 * @create 2023/2/28 17:55
 */
@Configuration  //定义此类为配置文件(即相当于之前的xml配置文件)
public class InterceptorConfig implements WebMvcConfigurer {

    //mvc:interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //要拦截user下的所有访问请求,必须用户登录后才可访问,
        // 但是这样拦截的路径中有一些是不需要用户登录也可访问的
        String[] addPathPatterns = {
                "/user/**"
        };

        //要排除的路径,排除的路径说明不需要用户登录也可访问
        String[] excludePathPatterns = {
                "/user/out", "/user/error", "/user/login"
        };

        //mvc:interceptor bean class=""
        registry.addInterceptor(new UserInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}

