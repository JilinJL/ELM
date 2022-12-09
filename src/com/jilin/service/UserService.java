package com.jilin.service;

import com.jilin.entity.User;


public interface UserService {

    //注册
    public int addUser(User user);
    //账号是否存在
    public int getUserById(String userId);
    //登录
    public User userLogin(String userId,String password);
}
