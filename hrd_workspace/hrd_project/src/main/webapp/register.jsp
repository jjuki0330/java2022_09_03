<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    //db연결정보
	Connection conn= null;
	PreparedStatement pst= null;
	ResultSet rs=null;
	
	//오늘 날짜 작업
	Calendar c = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//mm(시간), MM(월)
	String today=sdf.format(c.getTime());
/* 	out.print(today); */
	//가장 마직막에 추가된 회원번호 불러오기
	String sql="select max(custno) from member_tbl_02";
    
	try{
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","system","ezen");
		pst = conn.prepareStatement(sql);
		rs=pst.executeQuery();//select => executeQuery()
		int custno = 100001;
		if(rs.next()){
			custno = rs.getInt(1)+1;
		}
		%>
		
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
<script type="text/javascript">
function check_form(f){
	if(f.custname.value==""){
		alert("회원성명이 입력되지 않았습니다.");
		f.custname.focus();
		return false;
	}
	if(f.phone.value==""){
		alert("회원전화가 입력되지 않았습니다.");
		f.phone.focus();
		return false;
	}
	if(f.address.value==""){
		alert("주소가 입력되지 않았습니다.");
		f.address.focus();
		return false;
	}
	if(f.joindate.value==""){
		alert("가입일자가 입력되지 않았습니다.");
		f.joindate.focus();
		return false;
	}
	if(f.grade.value==""){
		alert("고객등급이 입력되지 않았습니다.");
		f.grade.focus();
		return false;
	}
	if(f.city.value==""){
		alert("도시코드가 입력되지 않았습니다.");
		f.city.focus();
		return false;
	}	
}
</script>
<section>
<!-- 본문 구역 -->
<h2>홈쇼핑 회원 등록</h2>
<form action="joinProcess.jsp" method="post" onsubmit="return check_form(this)">
<table>
	<tr>
		<th>회원번호(자동발생)</th>
		<td><input type="text" name="custno" value="<%= custno %>" readonly="readonly"></td>
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
		<td><input type="text" name="joindate" value="<%= today%>" readonly="readonly"></td>
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
<% 
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs!=null)rs.close();  
		if(pst != null)pst.close();
		if(conn != null)conn.close();
	}
    %>
</body>
</html>