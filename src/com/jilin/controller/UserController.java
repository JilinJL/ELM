package com.jilin.controller;

import com.jilin.entity.User;
import com.jilin.service.impl.UserServiceImpl;
import com.jilin.service.UserService;
import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserController {

    //注册
    public int saveUser(HttpServletRequest request)
    {
        User user = new User();
        user.setUserId(request.getParameter("userId"));
        user.setPassword(request.getParameter("password"));
        user.setUserName(request.getParameter("userName"));
        user.setUserSex(Integer.parseInt(request.getParameter("userSex")));
        UserService usersService = new UserServiceImpl();
        return usersService.addUser(user);
    }

    //账号是否存在
    public int getUserById(HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        UserService usersService = new UserServiceImpl();
        return usersService.getUserById(userId);
    }

    //登录
    public User getUserByIdByPass(HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService usersService = new UserServiceImpl();
        return usersService.userLogin(userId, password);
    }
}
