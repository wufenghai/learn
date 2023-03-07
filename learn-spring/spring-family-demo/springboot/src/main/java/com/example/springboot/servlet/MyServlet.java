package com.example.springboot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * https://blog.csdn.net/weixin_47872288/article/details/118615399?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163516746516780265439579%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=163516746516780265439579&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_v29-1-118615399.pc_v2_rank_blog_default&utm_term=servlet&spm=1018.2226.3001.4450
 *
 * springboot下使用servlet主要有两种方式
 *
 * 通过注解方式，主要有两个注解，第一个为路径@WebServlet，第二个为扫描路径的注解@ServletComponentScan
 * 通过配置类注册组件
 * @author wfh
 * @create 2023/2/28 17:59
 */
@WebServlet(urlPatterns = "/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("世界您好,Hello World!");
        //统一设置浏览器编码格式
        resp.setContentType("text/html;character=utf-8");
        resp.getWriter().flush();
        resp.getWriter().close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

