package com.example.redis.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wfh
 * @create 2023/3/7 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {

    private Long id;//一定使用包装类，实体类
    @TableField(value = "user_name")
    private String name;
    private String sex;
    private int age;

}