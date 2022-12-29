<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body onload="printCommentList(${bvo.bNo})">
<h1>detail page</h1>
<div>
	<img alt="이미지 없음" src="/_fileUpload/${bvo.image_file }"><br>
</div>
bNo: ${bvo.bNo }<br>
title: ${bvo.title }<br>
writer: ${bvo.writer }<br>
content: ${bvo.content}<br>
reg_date: ${bvo.reg_date }<br>
read_count: ${bvo.read_count }<br>

<div>
comment line<br>
<input type="text" id="cmtWriter" value="${ses.email }" readonly="readonly"><br>
<input type="text" id="cmtText" placeholder="Add Comment">
<button type="button" id="cmtAddBtn">댓글등록</button>

</div>
<!-- 댓글 표시 영역 -->
<div class="accordion" id="accordionExample">
	<div class="accordion-item">
	    <h2 class="accordion-header" id="headingOne">
	      <button class="accordion-button" type="button" data-bs-toggle="collapse"
	       data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	        cNo, bNo, writer 
	      </button>
	    </h2>
	    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
	      <div class="accordion-body">
	       content, reg_at
	      </div>
	    </div>
	</div>
</div>

<a href="/brd/modify?bNo=${bvo.bNo }"><button type="button">EDIT</button></a><br>
<a href="/brd/list"><button type="button">BOARD LIST</button></a>
<a href="/brd/remove?bNo=${bvo.bNo }"><button type="button">DELETE GEUL</button></a>
<script type="text/javascript">
	const msg_edit = '<c:out value="${msg_edit}" />';
	if(msg_edit == "0"){
		alert('수정할 권한이 없습니다.');
	}
	const msg_modify_login_null = '<c:out value="${msg_modify_login_null}" />';
	if(msg_modify_login_null=="0"){
		alert('로그인이 필요합니다.');
	}
</script>
<script type="text/javascript">	
	const bnoVal = '<c:out value="${bvo.bNo}" />';		
</script>


<script src="/resources/board_detail.js"></script>
<script type="text/javascript">
printCommentList(bnoVal);
</script>
</body>

</html>
