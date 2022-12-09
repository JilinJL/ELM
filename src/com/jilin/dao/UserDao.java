package com.jilin.dao;

import com.jilin.entity.User;

import java.util.List;

public interface UserDao {
    //注册
    public int addUser(User user);
    //账号是否存在
    public int getUserById(String userId);
    //登录
    public User userLogin(String userId,String password);
}
