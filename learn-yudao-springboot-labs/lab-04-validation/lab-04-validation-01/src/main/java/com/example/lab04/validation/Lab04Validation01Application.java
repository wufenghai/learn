package com.example.lab04.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class Lab04Validation01Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab04Validation01Application.class, args);
    }

}
