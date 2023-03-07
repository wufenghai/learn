package com.example.springboot.interceptor;

import com.example.springboot.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器的内容主要是书写拦截的内容逻辑
 * @author wfh
 * @create 2023/2/28 17:53
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入拦截器------------------------------");
        //编写业务拦截的规则
        //从session中获取用户的信息
        User user = (User) request.getSession().getAttribute("user");

        //判断用户是否登录
        if (null == user) {
            //未登录
            response.sendRedirect(request.getContextPath() + "/user/error");
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
