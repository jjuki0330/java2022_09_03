<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 페이지</title>
</head>
<body>
	<div class="GuestBook">Guest Book</div>
	<a href="/brd/post"><button type="button">Go to post</button></a>
	<div class="guestbookContainer">
	<c:forEach items="${list }" var="bvo">
		<a href="/brd/detail?bNo=${bvo.bNo }">
			<div class="guestOne">
				<c:if test="${bvo.image_file ne '' && bvo.image_file ne null }">
				<div class="guestimage"><img alt="" src="/_fileUpload/th_${bvo.image_file }"></div>
				</c:if>
				<div class="guestinfo">
					<div class="nickname">${bvo.writer }</div>
					<div class="reg_at">${bvo.reg_at }</div>
					<div class="content">${bvo.content }</div>
				</div>
			</div>
		</a>
	</c:forEach>
	</div>
	<div class="pagination">
		<a href="/mem/pagination?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}"> < </a>
		<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
			<a href="/mem/pagination?pageNo=${i }&qty=${pgh.pgvo.qty }">${i } | </a>		
		</c:forEach>
		<a href="/mem/pagination?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}"> > </a>
	</div>
</body>
</html>