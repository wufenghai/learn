package com.example.spring.data.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *      JPA是Java Persistence API的简称，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，
 *      并将运行期的实体对象持久化到数据库中。
 *      Sun引入新的JPA ORM规范出于两个原因：
 *      其一，简化现有Java EE和Java SE应用开发工作；
 *      其二，Sun希望整合ORM技术，实现天下归一。
 * JPA包括以下3方面的技术：
 *
 * ORM映射元数据： 支持XML和注解两种元数据的形式，元数据描述对象和表之间的映射关系
 * API： 操作实体对象来执行CRUD操作
 * 查询语言： 通过面向对象而非面向数据库的查询语言（JPQL）查询数据，避免程序的SQL语句紧密耦合
 *
 *
 *
 * 其实mybtais-plus完全可以替换掉jpa
 */
@SpringBootApplication
public class SpringdataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataJpaApplication.class, args);
    }

}
