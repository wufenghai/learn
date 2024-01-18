package com.example.demodependencycorebean;

import com.example.democorebean.bean.Cat;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
//@SpringBootApplication
public class DemoDependencyCoreBeanApplication {

    @Bean
    public Gson createGson(){
        return new Gson();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoDependencyCoreBeanApplication.class, args);
        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);
        System.out.println(context.getBeansOfType(Gson.class));
    }

}
