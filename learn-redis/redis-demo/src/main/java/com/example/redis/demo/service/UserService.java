package com.example.redis.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.redis.demo.entity.User;

/**
 * @author wfh
 * @create 2023/3/7 15:14
 */
public interface UserService extends IService<User> {
    //定义一个接口方法
    User findById(long id);
}
