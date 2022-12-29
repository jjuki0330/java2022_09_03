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
<h1>Board list page</h1>
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
 	<td>${bvo.bNo }</td>
 	<td>
 	<c:if test="${bvo.image_file ne '' && bvo.image_file ne null }">
 	<img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
 	</c:if>
 	<a href="/brd/detail?bNo=${bvo.bNo }">${bvo.title }</a>
 	</td>
 	<td>${bvo.writer }</td>
 	<td>${bvo.reg_date }</td>
 	<td>${bvo.read_count }</td>
 </tr>
</c:forEach>
</tbody>
</table>
<hr>
<c:forEach begin="1" end="${totalPage }" var="page">
      <a href="/brd/list?pageNum=${page }">${page }</a>
</c:forEach>
   
<a href="/"><button type="button">index로...</button></a>
<a href="/brd/post"><button type="button">post</button></a>
<script type="text/javascript">
const msg_remove = '<c:out value="${msg_remove}" />';
if(msg_remove == "0"){
	alert('삭제할 권한이 없습니다.');
}

</script>
</body>
</html>