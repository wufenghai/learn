package com.example.lab02.spring.session.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.AbstractMongoSessionConverter;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * @author wfh
 * @create 2023/4/18 11:21
 */
@Configuration
@EnableMongoHttpSession // 自动化配置 Spring Session 使用 MongoDB 作为数据源
public class SessionConfiguration {

    /**
     * 目前，Spring Session MongoDB 基于 MongoDB 自动过期删除过期数据的机制，实现 Session 的自动过期。
     * @return
     */
    @Bean
    public AbstractMongoSessionConverter mongoSessionConverter() {
        return new JacksonMongoSessionConverter();
    }
}
