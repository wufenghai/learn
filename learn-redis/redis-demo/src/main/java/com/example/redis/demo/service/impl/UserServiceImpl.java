package com.example.redis.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redis.demo.entity.User;
import com.example.redis.demo.mapper.UserMapper;
import com.example.redis.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    // 当调用这个方法的时候，会从一个名叫user的缓存中查询
    @Cacheable(cacheNames = "user", key = "#id")
    @Override
    public User findById(long id) {
        // 如果不存在则查询数据库,并把查询的结果放入缓存中
        return userMapper.selectById(id);
    }

   
}