package com.example.canaldemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class CanalDemoApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CanalDemoApplication.class, args);
    }

}
