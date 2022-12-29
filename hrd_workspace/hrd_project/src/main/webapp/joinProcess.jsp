<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//db연결정보
	Connection conn= null;
	PreparedStatement pst= null;
	
	//request encoding 설정
	request.setCharacterEncoding("utf-8");	
	//jsp에서 받아온 값 변수화
	String custno=request.getParameter("custno");
	String custname=request.getParameter("custname");
	String phone= request.getParameter("phone");
	String address = request.getParameter("address");
	String joindate = request.getParameter("joindate");
	String grade = request.getParameter("grade");
	String city = request.getParameter("city");
	
	String sql = "insert into member_tbl_02 values(?,?,?,?,?,?,?)";
	try{
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","system","ezen");
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.parseInt(custno));
		pst.setString(2,custname);
		pst.setString(3, phone);
		pst.setString(4, address);
		pst.setString(5, joindate);
		pst.setString(6, grade);
		pst.setString(7, city);
		int res= pst.executeUpdate();//insert, update, delete => executeUpdate();
		//res=isOk
		//list=> executeQuery()
		
		if(res>0){
			%>
			<script type="text/javascript">
			 	alert("회원등록이 완료되었습니다.");
			 	history.back();//뒤로... 다른 경로로 갈때 location.href='list.jsp'
			</script>
		<%}else{
			%>
			<script type="text/javascript">
				alert("회원등록이 실패되었습니다.");
				history.back();
			</script>
		<%}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		pst.close();
		conn.close();
	}
%>