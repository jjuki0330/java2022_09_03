<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
    <link rel="stylesheet" href="/resources/header.css">
<link rel="stylesheet" href="/resources/allstyle.css">
<link rel="stylesheet" href="/resources/modify.css">
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
    <div class="cotnainer">
        <div class="image"><img alt="" src="/_fileUpload/mainImage.jpg"></div>
        <div class="content">
            <div class="title">Edit Information</div>
                <div class="subcontent">
                    <form action="/mem/edit" method="post">
                    <div class="email">
                         <span class="name">email</span><input type="text" name="email" value="${ses.email }" readonly="readonly">
                    </div>
                    <div class="prior password">
                        <input type="hidden" name="priorPassword" id="priorPassword1" value="${ses.password }" >
                         <span class="name">password</span><input type="password" id="priorPassword2" onkeyup="priorPasswordCheck()"><div id="pOk">X</div>
                    </div>
                    <div class="new password">
                         <span class="name">new password</span><input type="password" name="password" id="newPassword" onkeyup="passwordCheck()">
                    </div>
                    <div class="password check">
                         <input type="password" id="passwordCheck" onkeyup="passwordCheck()"><div id="ok">X</div>
                    </div>
                    
                    <div class="nickname">
                         <span class="name">nickname</span><input type="text" name="nickname" value="${ses.nickname }">
                    </div>
                    
                    <div class="buttoncollection">
                        <button type="submit" class="btn">SUBMIT</button>
                        <a href="/"><button type="button" class="btn">CANCEL</button></a>
                    </div>
                    </form>
                </div>					
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
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
		 pCheck.style.color="red";
	 }
 }
 function priorPasswordCheck(){
	 var priorPwd1= document.getElementById('priorPassword1').value;
	 var priorPwd2= document.getElementById('priorPassword2').value;
	 var ppCheck = document.getElementById('pOk');
	 if(priorPassword1==priorPassword2){
		 ppCheck.innerText="O";
		 ppCheck.style.color="green";
	 }else{
		 ppCheck.innerText="X";
		 ppCheck.style.color="red";
	 }
 }
</script>
</body>
</html>