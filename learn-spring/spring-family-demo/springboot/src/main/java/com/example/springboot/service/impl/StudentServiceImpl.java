package com.example.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.model.Student;
import com.example.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wfh
 * @create 2023/2/28 17:15
 */
//@Service
@Transactional(rollbackFor = Exception.class)
/**
 * 服务提供者：
 *
 * 接口交给spring容器@Component
 * 暴露接口需要使用这个 @Service(interfaceClass = StudentService.class,version = "1.0.0",timeout = 15000)
 */
@Service(interfaceClass = StudentService.class,version = "1.0.0",timeout = 15000)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        String count = (String) redisTemplate.opsForValue().get(key);
        return count;
    }


    @Override
    public Integer queryAllStudentCount() {
        return null;
    }
}

