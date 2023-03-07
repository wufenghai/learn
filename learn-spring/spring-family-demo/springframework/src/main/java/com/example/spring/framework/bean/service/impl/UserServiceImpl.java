package com.example.spring.framework.bean.service.impl;

import com.example.spring.framework.bean.dao.UserDao;
import com.example.spring.framework.bean.service.UserService;

/**
 * @author wfh
 * @create 2023/3/1 9:59
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }

}
