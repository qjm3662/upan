<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/5 0005
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册测试</title>
</head>
<body>
<form action="/user/register" method="POST">
    <input type="text" name="username">  <br/>
    <input type="text" name="password"><br/>
    <input type="sex" name="sex" ><br/>
    <input type="submit" value="提交"><br/>
</form>
</body>
</html>
