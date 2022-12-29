<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>MODIFY PAGE</h1>
<form action="/mem/edit" method="post">
email: <input type="text" name="email" value="${mvo.email }" readOnly><br>
password:<input type="text" name="pwd" value="${mvo.pwd }"><br>
nickname:<input type="text" name="nick_name" value="${mvo.nick_name }"><br>
registerDate:<input type="text" name="reg_at" value="${mvo.reg_at }" readOnly><br>
lastLogin:<input type="text" name="last_login" value="${mvo.last_login }" readOnly><br>
<a href="/mem/login"><button type="submit">COMPLETE</button></a>
</form>
</body>
</html>