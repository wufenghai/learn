package com.example.spring.data.jpa.controller;

import com.example.spring.data.jpa.model.User;
import com.example.spring.data.jpa.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author wfh
 * @create 2023/3/1 19:30
 */

@RestController
public class UserController {
    @Autowired
    Repository repository;


    //    通过id获取用户
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        Optional<User> userOptional = repository.findById(id);
        User user = userOptional.get();
        return user;
    }

    //    新增用户
    @GetMapping("/user")
    public User addUser(User user){
        User user1 = repository.save(user);
        return user1;
    }
}