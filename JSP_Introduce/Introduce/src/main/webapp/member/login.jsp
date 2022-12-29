<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/header.css">
    <link rel="stylesheet" href="/resources/login.css">
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
        <div class="login"><span>Log In</span><a href="/"><div class="goback">X</div></a></div>
        <div class="loginContainer">
            <form action="/mem/login_auth" method="post">
            <div class="name email">
                Email
            </div>
            <div class="input">
            <input type="text" name="email">
            </div>
            <div class="name password">
                Password
            </div>
            <div class="input">
            <input type="password" name="password">
            </div>
            <button type="submit" class="login_btn">Log In</button>
            </form>
            <div class="signup">
                <a href="/member/signup.jsp">Do you want sign up?</a>
            </div>
        </div>
        </div> 
</body>
</html>