package com.example.demoselector.config;

import com.example.demoselector.bean.Cat;
import com.example.demoselector.bean.Dog;
import org.springframework.context.annotation.Bean;

/**
 * @author wfh
 * @create 2024/1/18 9:50
 */
public class MyConfig {

    @Bean
    public Dog dog(){
        return new Dog();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }
}
