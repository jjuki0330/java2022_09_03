<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/brd/edit" method="post" enctype="multipart/form-data">
 bNo: <input type="text" name="bNo" value="${bvo.bNo }" readOnly><br>
 title: <input type="text" name="title" value="${bvo.title }"><br>
 writer:<input type="text" name="writer" value="${bvo.writer }" readOnly><br>
 content: <textarea rows="3" cols="30" name="content">${bvo.content }</textarea><br>
 image_file: <input type="hidden" name="image_file" value="${bvo.image_file }"><br>
 			 <input type="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif">
 <br>
 <button type="submit">수정 완료</button><br>
</form>
 <a href="/brd/list"><button type="button">수정 취소</button></a>
</body>
</html>