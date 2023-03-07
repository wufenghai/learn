package com.example.spring.data.jpa.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

/**
 * @author wfh
 * @Index 注解用来添加表的索引，可以指定单个字段和多个字段,多个字段之间用 ’ , ’ 隔开
 * @create 2023/3/1 19:15
 */
@Entity
@Table(name = "sys_user", indexes = {
        @Index(name = "username", columnList = "username"),
        @Index(name = "address_age", columnList = "address,age")
})
public class User {
    /**
     * @Id 标识此字段是主键
     * @GeneratedValue 设置主键的自增策略, 默认是AUTO
     * TABLE 使用一个特定的数据库表格来保存主键。
     * SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY 主键由数据库自动生成（主要是自动增长型）
     * AUTO 主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, updatable = false, length = 36)
    private String username;

    private String address;
    private Integer age;

    /**
     * @Transien 就是在给某个javabean上需要添加个属性，但是这个属性你又不希望给存到数据库中去，
     * 仅仅是做个临时变量，用一下。不修改已经存在数据库的数据的数据结构。
     */
    @Transient
    private String ignoreColumn;

}

