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
 *
 * redis 缓存的使用
 *
 * @Cacheable
 * 该注解标注的方法每次被调用前都会触发缓存校验，校验指定参数的缓存是否已存在（已发生过相同参数的调用），若存在，直接返回缓存结果，否则执行方法内容，最后将方法执行结果保存到缓存中。
 * @CachePut
 * 它会每次调用方法，然后将缓存写到redis缓存中，并将结果返回。与@Cacheable不同的是，它不会检测在相同Cache中是否存在相同key的缓存元素。
 * @CacheEvict
 * 如果缓存中存在存在目标值，则将其从缓存中删除
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