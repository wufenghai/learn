package com.example.lab03.demo01.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wfh
 * @create 2023/4/18 14:57
 */
@RestController
@RequestMapping("/")
public class Callback02Controller {

    @GetMapping("/callback02")
    public String login() {
        return "假装这里有一个页面";
    }

}
