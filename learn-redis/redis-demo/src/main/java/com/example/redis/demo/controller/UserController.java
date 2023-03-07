package com.example.redis.demo.controller;

import com.example.redis.demo.entity.User;
import com.example.redis.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wfh
 * @create 2023/3/7 15:58
 */
@Api("contrller") //swagger标签
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "findById接口")//swagger标签
    @GetMapping("findById/{id}")//注意接口
    public User findById(@PathVariable Long id){
        User byId = userService.getById(id);
        return byId;
    }

    //测试redis缓存
    @ApiOperation(value = "findById接口")//swagger标签
    @GetMapping("findById2/{id}")//注意接口
    public User findById2(@PathVariable Long id){
        User user = userService.findById(id);
        return user;
    }
}