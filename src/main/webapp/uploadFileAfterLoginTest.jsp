<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/6 0006
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录后上传测试</title>
</head>
<body>
<form action="/user/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="number" name="isPublic" label>
    <input type="submit" value="提交">
</form>
</body>
</html>
