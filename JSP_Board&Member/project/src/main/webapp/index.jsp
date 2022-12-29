<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
*{
	text-align: center;
}
h1{
	color: white;
	font-weight: bold;
	background-color: pink;
}
button{
	width: 200px;
	height: 100px;
	margin: 10px auto;
}

</style>
</head>
<body>
<h1>MY FIRST PROJECT BOARD & MEMBER</h1>
<br>
<c:if test="${ses.email ne null && ses.email ne ''}">
	${ses.email }의 ${ses.nick_name }님이 로그인하였습니다.<br>
	계정생성일: ${ses.reg_at}<br>
	마지막 접속: ${ses.last_login }<br>
	<a href="/mem/modify?email=${ses.email }"><button type="button" class="btn btn-outline-secondary">EDIT INFO</button></a><br>
	<a href="/mem/logout?"><button type="button" class="btn btn-outline-secondary">LOGOUT</button></a><br>
</c:if>

<c:if test="${ses.email ne null && ses.email ne ''}">
	<a href="/brd/post?email=${ses.email}"><button type="button" class="btn btn-outline-secondary">GO TO POST</button></a><br>	
	<a href="/mem/list"><button type="button" class="btn btn-outline-secondary"> MEMBER LIST</button></a><br>
</c:if>


<c:if test="${ses.email eq null }">
<a href="/brd/list"><button type="button" class="btn btn-outline-secondary"> Board List</button></a><br>
<a href="/mem/login"><button type="button" class="btn btn-outline-secondary"> LOG IN</button></a><br>
<a href="/mem/join"><button type="button" class="btn btn-outline-secondary"> SIGN UP</button></a><br>
</c:if>


<c:if test="${ses.email ne null && ses.email ne ''}">
	<a href="/mem/remove?email=${ses.email }"><button type="button" class="btn btn-outline-secondary">회원탈퇴</button></a><br>
</c:if>

</body>
</html>