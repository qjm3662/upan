<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/7 0007
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>反馈测试</title>
</head>
<body>
<form action="/user/feedback" method="post">
    <label>
        手机号：<input type="text" name="phoneNumber">
        反馈内容：<input type="text" name="text">
        <input type="submit" value="提交">
    </label>
</form>
</body>
</html>
