package com.example.redis.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.redis.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wfh
 * @create 2023/3/7 15:08
 */
@Mapper
@Repository("UserMapper")
public interface UserMapper extends BaseMapper<User> {
}