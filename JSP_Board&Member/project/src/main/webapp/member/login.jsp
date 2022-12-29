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
	h1{
		margin-top: 100px;
		color: pink;
		text-align: center;
		font-weight: bold;
	}
	.container{
		margin: 50px auto;
		width:550px;

	}
	.mb-3{
		font-weight: bold;
		width: 500px;
	}
	button{
		margin-left: 15px;
		
	}

</style>
</head>
<body>
<h1>LOGIN PAGE</h1>
<form action="/mem/login_auth" method="post">
	<div class=container>
	<div class="mb-3">
  	<label for="exampleFormControlInput1" class="form-label">Email</label>
	<input type="text" name="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com"><br>
	</div>
	<div class="mb-3">
  	<label for="exampleFormControlInput1" class="form-label">Password</label>
	<input type="password" name="pwd" class="form-control" id="exampleFormControlInput1"><br>
	</div>
	<button type= submit class="btn btn-outline-secondary"> login </button>
	</div>
</form>
<script type="text/javascript">
	const msg_login = '<c:out value="${msg_login}" />';
	if(msg_login == '0'){
		alert("로그인 실패");
	}
	const msg_remove_login_null = '<c:out value="${msg_remove_login_null}" />';
	if(msg_remove_login_null=="0"){
		alert('로그인이 필요합니다.');
	}
	const msg_modify_login_null = '<c:out value="${msg_modify_login_null}" />';
	if(msg_modify_login_null=="0"){
		alert('로그인이 필요합니다.');
	}
</script>
</body>
</html>