package com.example.democonditional.config;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 如果我们不在项目中配置，spring-boot-autoconfigure会自动帮我们装配一个对象实例名为gson的Gson实例。
 * <p>
 * 如果自己装配那么就使用自己装配的Gson实例。
 *
 * @author wfh
 * @create 2024/1/18 10:51
 */
@Configuration
@ConditionalOnClass(Gson.class)
public class GsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Gson gson() {
        return new Gson();
    }
}
