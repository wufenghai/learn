package com.example.spring.data.jdbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author wfh
 * @create 2023/3/1 17:13
 */
@Data //用lombok来消除模板代码
public class Account {
    @Id  //标记属性为主键
    private Long id;
    private String loginName;
    private String password;

    //1.如果有无参数的构造函数， spring data jdbc会使用无参数的构造函数来创建对象
    //2.如果只有一个构造函数， spring data jdbc会使用它
    //3.如果有多个构造函数， spring data jdbc会使用有@PersistenceConstructor标记的那个
    @PersistenceConstructor
    public Account(Long id, String loginName, String password) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
    }
}
