package com.example.spring.framework.bean.factory;

import com.example.spring.framework.bean.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author wfh
 * @create 2023/3/1 9:53
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
