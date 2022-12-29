<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
	<%
	  //db연결정보
	  	Connection conn= null;
	  	PreparedStatement pst= null;
	  	ResultSet rs=null;
	  	
	  //모든 회원들 가져오기
	  	String sql="select * from member_tbl_02";
	      
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
	<li><a href="list.jsp">회원목록조회/수정</a></li>
	<li><a href="#">회원매출조회</a></li>
	<li><a href="/">메인화면</a></li>
</ul>
</nav>
<section>
<!-- 본문 구역 -->
<h2>홈쇼핑 회원목록 조회</h2>
<table>
	<tr>
		<th>회원번호</th>
		<th>회원성명</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>가입일자</th>
		<th>고객등급</th>
		<th>거주지역</th>
	</tr>
	<%
	while(rs.next()){
		//고객 등급에 대한 분류
		String grade = "";
		if(rs.getString(6).equals("A"))grade="VIP";
		if(rs.getString(6).equals("B"))grade="일반";
		if(rs.getString(6).equals("C"))grade="직원";
		
	
	%>
	<tr>
		<td><a><%=rs.getInt(1) %></a></td>
		<td><%=rs.getString(2) %></td>
		<td><%=rs.getString(3) %></td>
		<td><%=rs.getString(4) %></td>
		<td><%=rs.getString(5) %></td>
		<td><%=grade %></td>
		<td><%=rs.getString(7) %></td>
	</tr>
	<%
	}
	%>
</table>
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