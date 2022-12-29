<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" href="/resources/header.css">
<link rel="stylesheet" href="/resources/register.css">
<link rel="stylesheet" href="/resources/allstyle.css">
</head>
<body>
   <header>
        <ul>
            <li><div class="nav one"></div></li>
            <li><div class="nav two"></div></li>
            <li><div class="nav three"></div></li>
            <li><div class="nav four"></div></li>
        </ul>
   </header>
      <!-- menu bar -->
      <div class="menubar">
          <c:if test="${ses.email eq null || ses.email eq '' }">
          <a href="/mem/login"><button type="button">Log in</button></a>
          <a href="/mem/join"><button type="button">Sign up</button></a>
          </c:if>
          <c:if test="${ses.email ne null && ses.email ne ''}">
          <a href="/mem/log_out"><button type="button">Log out</button></a>
          <a href="/mem/detail"><button type="button">My page</button></a>
          </c:if>
          <a href="/brd/list"><button type="button">Guest book</button></a>
      </div>
     <!-- main화면  -->
	<div class="main">
		<div class="maincontainer ">
			<div class="img">
				<img alt="사진없음" src="/_fileUpload/mainImage.jpg">
			</div>
			<div class="maincontent">
				<h1>WELCOME JJuKi WORLD!</h1>
				<p>안녕하세요!<br>열정 넘치는 신입 개발자 쭈키입니다.<br>만나서 반가워요!</p>
				<p>Hello! I'm jjuki, junior developer with passion<br>
				Thank you for visiting my introduction page</p>
			</div>
		</div>
		<div>참고 앨범</div>
	</div>
<div class="cover">
<div class="container">
	<div class="image"><img alt="사진 없음" src="/_fileUpload/mainImage.jpg"></div>
	<div class="content">
		<div class="title">Sign up</div>
		<form action="/mem/register" method="post">
			<div class="subcontent">
				<div class="email">
				 	<span class="name">email</span><input type="text" name="email">
				</div>
				<div class="password">
				 	<span class="name">password</span><input type="password" id="password1" name="password" onkeyup="passwordCheck()">
				</div>
				<div class="passwordcheck">
				 	<input type="password" id="password2" onkeyup="passwordCheck()"><div id="ok">X</div>
				</div>
				<div class="nickname">
				 	<span class="name">nickname</span><input type="text" name="nickname" >
				</div>
				
				
				<div class="SNS">
					<button type="button">Sign up with KakaoTalk</button>
					<button type="button">Sign up with Facebook</button>
				</div>
				
				<div class="submitcancel">
					<a href="/"><button type="submit" class="btn">SUBMIT</button></a>
					<a href="/"><button type="button" class="btn">CANCEL</button></a>
				</div>
			</div>					
		</form>
	</div>
</div>
</div>
<script type="text/javascript">
 const msg_email_check='<c:out value="${msg_email_check}"/>';
 const msg_register_fail='<c:out value="${msg_register_fail}"/>';
 if(msg_email_check=="0"){
	 console.log("중복된 이메일");
	 alert('중복된 이메일입니다.');
 }
 if(!msg_register_fail=="0"){
	 alert('모두 입력해야합니다.');
 }
 
 function passwordCheck(){
 	var password1= document.getElementById('password1').value;
 	var password2= document.getElementById('password2').value;
	 var pCheck= document.getElementById('ok');
	 console.log(password1,password2);
	 if(password1==password2){
		 pCheck.innerText="O";
         pCheck.style.color="green";
	 }else{
		 pCheck.innerText="X";
	 }
 }
</script>
</body>
</html>
