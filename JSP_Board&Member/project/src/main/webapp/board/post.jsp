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
<h1>post page</h1>

<c:if test="${ses.email ne null && ses.email ne ''}">
<form action="/brd/insert" method="post" enctype="multipart/form-data">
<div class="container">
	<div>title: <input type="text" name="title"></div>
	<div>writer:<input type = "text" name="writer" value="${ses.email }" readonly></div>
	<div>content: <textarea rows="3" cols="30" name="content"></textarea></div>
	<div>image_file: <input type="file" id="file" name="image_file" accept="image/png image/jpg, image/jpeg, image/gif"></div>
	<button type="submit">완료</button>	
</div>
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Email address</label>
  <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>
</form>
</c:if>
</body>
</html>