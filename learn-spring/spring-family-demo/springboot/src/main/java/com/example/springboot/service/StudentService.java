package com.example.springboot.service;

import com.example.springboot.model.Student;

/**
 * @author wfh
 * @create 2023/2/28 17:15
 */
public interface StudentService {
    /**
     * 根据学生ID查询详情
     * @param id
     * @return
     */
    Student queryStudentById(Integer id);

    /**
     * 将值存放到redis中
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 从redis中获取指定key的值
     * @param key
     * @return
     */
    String get(String key);

    Integer queryAllStudentCount();
}
