package com.example.demoselector;

import com.example.demoselector.annotation.EnableLog;
import com.example.demoselector.bean.Cat;
import com.example.demoselector.bean.Dog;
import com.example.demoselector.bean.People;
import com.example.demoselector.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableLog(name = "com.example.demoselector")
public class DemoSelectorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoSelectorApplication.class,args);
        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        System.out.println(context.getBean(People.class));
    }

}
