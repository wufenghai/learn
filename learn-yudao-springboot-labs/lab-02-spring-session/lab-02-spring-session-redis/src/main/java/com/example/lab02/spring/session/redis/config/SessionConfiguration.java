package com.example.lab02.spring.session.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wfh
 * @create 2023/4/18 11:02
 */
@Configuration
@EnableRedisHttpSession // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {

    /**
     * 采用 JSON 序列化方式。因为默认情况下，采用 Java 自带的序列化方式 ，可读性很差，所以进行替换。
     * @return
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }
}
