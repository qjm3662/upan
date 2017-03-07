<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/7 0007
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码测试</title>
</head>
<body>
<form action="/user/revisePSD" method="post">
    <label>
        旧密码：<input type="text" name="oldPassword">
        新密码：<input type="text" name="newPassword">
        <input type="submit" value="提交">
    </label>
</form>
</body>
</html>
