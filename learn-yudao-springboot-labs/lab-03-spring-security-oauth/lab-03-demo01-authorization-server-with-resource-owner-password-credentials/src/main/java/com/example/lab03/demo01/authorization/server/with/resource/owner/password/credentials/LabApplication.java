package com.example.lab03.demo01.authorization.server.with.resource.owner.password.credentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 密码模式，用户向客户端提供自己的用户名和密码。客户端使用这些信息，向授权服务器索要授权。
 * （A）用户向客户端提供用户名和密码。
 * （B）客户端将用户名和密码发给授权服务器，向后者请求令牌。
 * （C）授权服务器确认无误后，向客户端提供访问令牌。
 */
@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

}
