package com.example.democorebean.config;

import com.example.democorebean.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wfh
 * @create 2024/1/17 17:46
 */
@Configuration
public class MyConfig {

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
