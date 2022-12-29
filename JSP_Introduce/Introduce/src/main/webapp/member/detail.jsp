<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<link rel="stylesheet" href="/resources/header.css">
<link rel="stylesheet" href="/resources/allstyle.css">
<link rel="stylesheet" href="/resources/detail.css">

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
	<div class="image"><img alt="" src="/_fileUpload/mainImage.jpg"></div>
	<div class="content">
		<div class="title">My page</div>
			<div class="subcontent">
				<div class="email">
				 	<span class="name">email</span><input type="text" name="email" value="${ses.email }">
				</div>
				<div class="password">
				 	<span class="name">password</span><input type="password" value="${ses.password }" name="password">
				</div>
				<div class="nickname">
				 	<span class="name">nickname</span><input type="text" name="nickname" value="${ses.nickname }">
				</div>
				
				<div class="buttoncollection">
					<a href="/mem/modify"><button type="submit" class="btn">EDIT</button></a>
					<a href="/"><button type="button" class="btn">HOME</button></a>
					<a href="/mem/delete?email=${ses.email }"><button type="button" class="btn">DEACTIVATE</button></a>
					<c:if test="${ses.email eq 'ghdwnsgml123@naver.com'}">
					<a href="/mem/list"><button type="button" class="btn mem">MEMBER LIST</button></a>
					</c:if>
				</div>
			</div>					
	</div>
</div>
</div>
</body>
</html>