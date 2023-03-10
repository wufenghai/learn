package com.example.redis.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wfh
 * @create 2023/3/9 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFriends {
    private Long id;
    private String name;
    private String sex;
    private int age;

}
