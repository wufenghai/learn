package com.example.springboot.mapper;

import com.example.springboot.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wfh
 * @create 2023/2/28 17:16
 */
@Mapper
public interface StudentMapper {
    Student selectByPrimaryKey(Integer id);
}
