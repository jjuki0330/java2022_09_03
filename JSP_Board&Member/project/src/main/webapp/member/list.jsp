<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>LIST PAGE</h1>
<table border="1">
 <thead>
 	<tr>
 		<th>EMAIL</th>
 		<th>PASSWORD</th>
 		<th>NICKNAME</th>
 		<th>REGISTER DATE</th>
 		<th>LAST_LOGIN</th>
 	</tr>
 </thead>
 <tbody>
	<c:forEach items="${list }" var="mvo">
		<tr>
			<td>${mvo.email }</td>
			<td>${mvo.pwd }</td>
			<td>${mvo.nick_name }</td>
			<td>${mvo.reg_at }</td>
			<td>${mvo.last_login }</td>
		</tr>
	</c:forEach>
 </tbody>
</table>
<a href="/"><button type="button">메인화면으로</button></a>
</body>
</html>