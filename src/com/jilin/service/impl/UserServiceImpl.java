package com.jilin.service.impl;

import com.jilin.dao.UserDao;
import com.jilin.dao.impl.UserDaoImpl;
import com.jilin.entity.User;
import com.jilin.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public int addUser(User user) {
        UserDao dao = new UserDaoImpl();
        return dao.addUser(user);
    }

    @Override
    public int getUserById(String userId) {
        UserDao dao = new UserDaoImpl();
        return dao.getUserById(userId);
    }

    @Override
    public User userLogin(String userId, String password) {
        UserDao dao = new UserDaoImpl();
        return dao.userLogin(userId, password);
    }
}
