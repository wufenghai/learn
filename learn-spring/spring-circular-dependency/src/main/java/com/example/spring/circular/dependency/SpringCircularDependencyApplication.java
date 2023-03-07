package com.example.spring.circular.dependency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 循环依赖
 */
@SpringBootApplication
public class SpringCircularDependencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCircularDependencyApplication.class, args);
    }

}
