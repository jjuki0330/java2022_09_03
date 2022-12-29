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
<table border="1">
<thead>
	<tr>
		<td>bNo</td>
		<td>title</td>
		<td>writer</td>
		<td>reg_date</td>
		<td>read_count</td>
	</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="bvo">
 <tr>
 	<td><a href="/brd/detail?bNo=${bvo.bNo }">${bvo.bNo }</a></td>
 	<td>${bvo.title }</td>
 	<td>${bvo.writer }</td>
 	<td>${bvo.reg_date }</td>
 	<td>${bvo.read_count }</td>
 </tr>
</c:forEach>
</tbody>
</table>
<div>
	<c:if test="${pgh.prev}">
		<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty } ">◀</a>
	</c:if>
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}">${i }｜</a>
	</c:forEach>
	<c:if test="${pgh.next}">
		<a href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty } ">▶</a>
	</c:if>
</div>
</body>
</html>