<%--
  Created by IntelliJ IDEA.
  User: jilin
  Date: 2022/12/1
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
    <style>
        .form_block{
            display: flex;
        }
        .form_block>*{
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<form class="form_block" action="userController/addUser" method="post">
    <input type="hidden" name="type" value="add">

    <label for="username">用户名:</label>
    <input id="username" type="text" name="username"> <br>
    <label for="userpsd">密码:</label>
    <input id="userpsd" type="userpsd" name="userpsd"> <br>

    <button type="submit" style="padding: 0.5rem;">添加</button>
</form>
</body>
</html>
