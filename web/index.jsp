<%--
  Created by IntelliJ IDEA.
  User: jilin
  Date: 2022/12/1
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>

    </style>
  </head>
  <body>
  <form action="userController/getList" method="get">
    <input type="hidden" name="type" value="list">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="登陆">
  </form>
  <a href="add.jsp">添加数据</a>
  </body>
</html>
