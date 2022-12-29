<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 상세페이지</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
<div class="contentContainer">
	<div class="contentImage"><img alt="" src="/_fileUpload/${bvo.image_file }"></div>
	<div class="contentText">
		<div class="writer">${bvo.writer }</div>
		<div class="reg_at">${bvo.reg_at }</div>
		<div class="content">${bvo.content }</div>
		<div class="heartContainer">
			<span class="material-symbols-outlined">
				favorite
			</span><div class="heart">${bvo.heart }</div>
			<c:if test="${ses.nickname eq bvo.writer}">
			<div class="editDel">
				<a href="/brd/modify?bNo=${bvo.bNo }"><span class="material-symbols-outlined">edit</span></a>
				<a href="/brd/delete?bNo=${bvo.bNo }"><span class="material-symbols-outlined">delete</span></a>
			</div>			
			</c:if>
		</div>
	</div>
</div>
</body>
</html>