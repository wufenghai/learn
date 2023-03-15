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
 * redis 缓存的使用（不管分布式锁的问题，后面有需要就用redission）
 *
 * @Cacheable（查询的时候用）
 * 该注解标注的方法每次被调用前都会触发缓存校验，校验指定参数的缓存是否已存在（已发生过相同参数的调用），若存在，直接返回缓存结果，否则执行方法内容，最后将方法执行结果保存到缓存中。
 * @CachePut（修改的时候用）
 * 它会每次调用方法，然后将缓存写到redis缓存中，并将结果返回。与@Cacheable不同的是，它不会检测在相同Cache中是否存在相同key的缓存元素。（双写，都用这个）
 * @CacheEvict（删除的时候用）
 * 如果缓存中存在存在目标值，则将其从缓存中删除（先删，查询的时候再存）
 * @author wfh
 * @create 2023/3/7 15:14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    //测试缓存
    @Override
    @Cacheable(cacheNames = "user", key = " 'id' ")//或者这样写key = "#id"
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

    // 先执行方法体中的代码，成功执行之后删除缓存
    @CacheEvict(cacheNames = "user", key = "#id") //cache:user:user::1 ; cache:user:user::2
    public boolean delete(Long id) {
        // 删除数据库中具有的数据
        return userMapper.deleteById(id) == 1;
    }

    // 如果缓存中先前存在，则更新缓存;如果不存在，则将方法的返回值存入缓存
    @CachePut(cacheNames = "user", key = "#user.id")
    public User update(User user) {
        userMapper.updateById(user);
        return user;
    }

    @CachePut(cacheNames = "user", key = "#user.id")
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

}
