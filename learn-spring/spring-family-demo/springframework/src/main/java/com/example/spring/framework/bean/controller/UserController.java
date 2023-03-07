package com.example.spring.framework.bean.controller;

import com.example.spring.framework.bean.service.UserService;

/**
 * @author wfh
 * @create 2023/3/1 9:56
 */
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser(){
        userService.saveUser();
    }

}

