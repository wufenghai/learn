package com.example.redis.demo.controller;

import com.example.redis.demo.entity.User;
import com.example.redis.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "delete接口")//swagger标签
    @DeleteMapping("delete/{id}")//注意接口
    public String delete(@PathVariable Long id){
        boolean user = userService.delete(id);
        if (user) {
            return "删除成功";
        }
        return "删除失败";
    }

    @ApiOperation(value = "update接口")//swagger标签
    @PostMapping("update")//注意接口
    public User update(@RequestBody User user){
        User temp = userService.update(user);
        return temp;
    }

    @ApiOperation(value = "insert接口")//swagger标签
    @PostMapping("insert")//注意接口
    public User insert(@RequestBody User user){
        User temp = userService.insert(user);
        return temp;
    }
}