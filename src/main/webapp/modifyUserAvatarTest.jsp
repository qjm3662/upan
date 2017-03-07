<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/7 0007
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户上传头像测试</title>
</head>
<body>
<form action="/user/modifyAvatar" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>
</body>
</html>
