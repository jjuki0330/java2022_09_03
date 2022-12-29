<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
<!-- 헤더 구역 -->
<header>
<h1>쇼핑몰 회원관리 ver 1.0</h1>

</header>
<!-- 메뉴 구역 -->
<nav>
<ul>
	<li><a href=register.jsp">회원등록</a></li>
	<li><a href="#">회원목록조회/수정</a></li>
	<li><a href="#">회원매출조회</a></li>
	<li><a href="/">메인화면</a></li>
</ul>
</nav>
<section>
<!-- 본문 구역 -->
<h2>홈쇼핑 회원 등록</h2>
<form action="#" method="post">
<table>
	<tr>
		<th>회원번호(자동발생)</th>
		<td><input type="text" name="custno" value="" readonly="readonly"></td>
	</tr>
	<tr>
		<th>회원성명</th>
		<td><input type="text" name="custname"></td>
	</tr>
	<tr>
		<th>회원전화</th>
		<td><input type="text" name="phone"></td>
	</tr>
	<tr>
		<th>회원주소</th>
		<td><input type="text" name="address"> </td>
	</tr>
	<tr>
		<th>가입일자</th>
		<td><input type="text" name="joindate"></td>
	</tr>
	<tr>
		<th>고객등급[A:VIP,B:일반, C:직원]</th>
		<td><input type="text" name="grade"></td>
	</tr>
	<tr>
		<th>도시코드</th>
		<td><input type="text" name="city"></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록" > &nbsp;
			<input type="button" value="조회"  onclick="location.href='list.jsp'" >
		</th>
	</tr>
</table>
</form>
</section>

<footer>
<!-- 하단 구역 -->
<p>HRDKOREA Copyright&copy;2016 All rights reserved Human Resources Development Service of Korea</p>
</footer>
</body>
</html>