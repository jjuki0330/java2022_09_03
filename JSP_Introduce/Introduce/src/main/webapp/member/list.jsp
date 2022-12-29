<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
    <link rel="stylesheet" href="/resources/header.css">
    <link rel="stylesheet" href="/resources/list.css">
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
			<img alt="사진없음" src="/image/mainImage.jpg">
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
    <div class="title"><span>Member List</span><a href="/mem/detail"><button type="button" class="gobackbtn">X</button></a></div>
    <div class="container">
        <div class="membercount">현재 회원 수: ${list.size() } </div>
        <table class="membertable">
            <thead>
                <tr>
                    <th>이메일</th>
                    <th>비밀번호</th>
                    <th>닉네임</th>
                    <th>가입일자</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list }" var="mvo">
                    <tr>
                        <td>${mvo.email }</td>
                        <td>${mvo.password }</td>
                        <td>${mvo.nickname }</td>
                        <td>${mvo.reg_at }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <a href="/mem/pagination?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}"> < </a>
            <c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
                <a href="/mem/pagination?pageNo=${i }&qty=${pgh.pgvo.qty }">${i } | </a>		
            </c:forEach>
            <a href="/mem/pagination?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}"> > </a>
        </div>
    </div>
</div>
</body>
</html>