package com.example.lab03.demo01.authorization.server.with.authorization.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权码模式，是功能最完整、流程最严密的授权模式。它的特点就是通过客户端的后台服务器，与授权务器进行互动。
 * （A）用户访问客户端，后者将前者跳转到到授权服务器。
 * （B）用户选择是否给予客户端授权。
 * （C）假设用户给予授权，授权服务器将跳转到客户端事先指定的"重定向 URI"（Redirection URI），同时附上一个授权码。
 * （D）客户端收到授权码，附上早先的"重定向 URI"，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
 * （E）认证服务器核对了授权码和重定向 URI，确认无误后，向客户端发送访问令牌。
 */
@SpringBootApplication
public class Lab03Demo01AuthorizationServerWithAuthorizationCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab03Demo01AuthorizationServerWithAuthorizationCodeApplication.class, args);
    }

}
