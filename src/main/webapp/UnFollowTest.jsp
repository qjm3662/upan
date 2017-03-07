<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/7 0007
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>取消关注测试</title>
</head>
<body>
<form action="/user/unFollow" method="POST">
    <input type="text" name="targetUsername">     <br/>
    <input type="submit", value="提交">
</form>
</body>
</html>
