package com.example.demohttprequest.controller;

import com.example.demohttprequest.bean.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author wfh
 * @create 2024/1/23 11:10
 */
@RestController
@RequestMapping("/http")
public class HttpController {

    @GetMapping("/get-test")
    public String getTest(@RequestParam("id") String id) {
        return "getTest";
    }

    @PostMapping("/post-test")
    public String postTest(@RequestParam("id") String id) {
        return "postTest  " + id;
    }

    @PostMapping("/post-test1")
    public String postTest1(@RequestBody User user) {
        return "postTest  :  " + user.getName();
    }

    @PostMapping("/post-test2")
    public String postTest2(@RequestParam("id") String id, @RequestBody User user) {
        return "postTest  :  " + id + ":" + user.getName() + ": " + user.getAge();
    }


}
