package com.example.spring.framework.bean.dao.impl;

import com.example.spring.framework.bean.dao.UserDao;

/**
 * @author wfh
 * @create 2023/3/1 10:00
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }

}
