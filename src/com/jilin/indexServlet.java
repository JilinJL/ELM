package com.jilin;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name="indexServlet", urlPatterns = "/")
public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        start(req, resp);
    }

    private void start(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        //反射获取[path]路径
        //获取路径字符串
        String path = req.getServletPath();
        //获取类名称
        String className = path.substring(1, path.lastIndexOf("/"));
        //获取方法名称
        String methodName = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(className + "," + methodName);
        //通过反射调用类的方法
        try {
            Class<?> clazz = Class.forName("com.jilin.controller." + className);
            //创建类的对象 相当于new了一个
            Object instance = clazz.getConstructor().newInstance();
            Method method = clazz.getMethod(methodName,HttpServletRequest.class);
            //调用方法
            Object object = method.invoke(instance, req);
            writer.write(JSON.toJSONString(object));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
