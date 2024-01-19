package com.example.demojmeter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wfh
 * @create 2024/1/18 17:32
 */
@RestController
@RequestMapping("/jmeter")
public class JmeterController {

    @GetMapping("/test")
    public String jmeterTest(){
        return "jmeter";
    }
}
