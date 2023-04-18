package com.example.lab03.demo01.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 很少项目会直接采用 Spring Security OAuth 框架，而是自己参考它进行 OAuth2.0 的实现。并且，一般只会实现密码授权模式。
 */
@SpringBootApplication
public class Lab03Demo01ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab03Demo01ResourceServerApplication.class, args);
    }

}
