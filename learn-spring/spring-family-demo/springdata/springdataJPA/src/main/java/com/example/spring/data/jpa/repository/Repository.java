package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wfh
 * @create 2023/3/1 19:29
 */
//自定义接口继承JpaRepository，就会有增删改查操作且分页排序 等功能

//指定的泛型<操作的实体类，主键的数据类型>
public interface Repository extends JpaRepository<User, Integer> {
}
