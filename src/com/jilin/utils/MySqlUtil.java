package com.jilin.utils;

import java.sql.*;

public class MySqlUtil {
    //封装连接数据库
    public static Connection getConn(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//        3. 创建连接
            String url = "jdbc:mysql://127.0.0.1:3306/elm?useUnicode=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "248789";
            con = DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public static void close(ResultSet rs, Statement stmt, Connection con){
        try{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if( con!=null){
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //重载用于 Pst
    public static void close(PreparedStatement pst, Connection con){
        try{
            if(pst!=null){
                pst.close();
            }
            if( con!=null){
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
