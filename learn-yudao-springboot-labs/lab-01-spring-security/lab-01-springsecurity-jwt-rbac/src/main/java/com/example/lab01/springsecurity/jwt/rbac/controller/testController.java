package com.example.lab01.springsecurity.jwt.rbac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wfh
 * @create 2023/4/18 17:19
 */
@RestController
@RequestMapping("/common")
public class testController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/inner")
    public String inner(){
        return "inner";
    }
}