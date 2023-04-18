package com.example.lab03.demo01.authorization.server.with.client.credentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端模式，指客户端以自己的名义，而不是以用户的名义，向授权服务器进行认证。
 *严格地说，客户端模式并不属于 OAuth 框架所要解决的问题。在这种模式中，用户直接向客户端注册，客户端以自己的名义要求授权服务器提供服务，其实不存在授权问题。
 * （A）客户端向授权服务器进行身份认证，并要求一个访问令牌。
 * （B）授权服务器确认无误后，向客户端提供访问令牌。
 */
@SpringBootApplication
public class Lab03Demo01AuthorizationServerWithClientCredentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab03Demo01AuthorizationServerWithClientCredentialsApplication.class, args);
    }

}
