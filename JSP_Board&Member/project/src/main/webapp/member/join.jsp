<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JOIN PAGE</h1>
<form action="/mem/register" method="post">
	EMAIL: <input type="text" name="email"><br>
	PASSWORD: <input type="password" name="pwd"><br>
	NICKNAME: <input type="text" name="nick_name"><br>
	
	<button type= submit> JOIN </button>
</form>
<script type="text/javascript">
 const email_dupli_msg='<c:out value="${email_dupli_msg}"/>';
 if(email_dupli_msg=="0"){
	 alert('email이 중복되었습니다.');
 }
</script>
</body>
</html>