package com.example.redis.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redis.demo.entity.User;
import com.example.redis.demo.mapper.UserMapper;
import com.example.redis.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wfh
 * @create 2023/3/7 15:14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    //测试缓存
    @Override
    @Cacheable(cacheNames = "user111", key = " 'id' ")//或者这样写key = "#id"
    public User findById2(long id) {
        User user = userMapper.selectById(id);
        return user;
    }
}