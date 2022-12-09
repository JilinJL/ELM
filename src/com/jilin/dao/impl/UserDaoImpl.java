package com.jilin.dao.impl;

import com.jilin.dao.UserDao;
import com.jilin.entity.User;
import com.jilin.utils.MySqlUtil;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        int ret = 0;
//        1. 导入jar包
//        2. 注册驱动
        try {
//        3. 创建连接
            Connection con = MySqlUtil.getConn();
//        4. 定义SQL语句
//        5. 执行SQL并处理结果
            String sql = "INSERT INTO user(userId, password, userName, userSex)values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getUserId());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUserName());
            pst.setInt(4, user.getUserSex());
            ret = pst.executeUpdate();
//        6. 释放资源
            MySqlUtil.close(pst,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getUserById(String userId) {

        try {
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT COUNT(*) as count FROM user where userId = '"+userId+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
//        6. 释放资源
            MySqlUtil.close(rs,stmt,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User userLogin(String userId, String password) {
        try {
            Connection con = MySqlUtil.getConn();
            String sql = "select * from user where userId='"+userId+"' and password='"+password+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getString("password"));
                user.setUserName(rs.getString("userName"));
                user.setUserSex(rs.getInt("userSex"));
                user.setUserImg(rs.getString("userImg"));
                user.setDelTag(rs.getInt("delTag"));

                return user;
            }
//        6. 释放资源
            MySqlUtil.close(rs,stmt,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
