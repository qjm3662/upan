<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/6 0006
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息设置</title>
</head>
<body>
    <form action="/user/ModifyUserInfoAction" method="post">
        <label>
            用户昵称：<input type="text" name="nickname">
            个性签名：<input type="text" name="signature">
            性别：<input type="number" name="sex">
            <input type="submit" value="提交">
        </label>
    </form>
</body>
</html>
