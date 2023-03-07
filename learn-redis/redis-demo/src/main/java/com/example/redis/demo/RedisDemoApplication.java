package com.example.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * http://localhost:8080/swagger-ui.html#/user-controller/updateUsingPOST
 */
@SpringBootApplication
@EnableCaching
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

}
