package com.example.lab02.spring.session.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wfh
 * @create 2023/4/18 11:31
 */
@Configuration
@EnableRedisHttpSession // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {
}
