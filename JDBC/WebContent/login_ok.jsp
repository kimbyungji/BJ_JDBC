<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String dbid = null;
	String dbpw = null;
	
	String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
	String driver = "com.mysql.cj.jdbc.Driver";
	String user = "mytest";
	String password = "mytest";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		// 드라이버 로드
		Class.forName(driver);
		
		// Connection 객체 생성
		conn = DriverManager.getConnection(url, user, password);  //SQL
		
	} catch (ClassNotFoundException ce) {
		System.out.println("드라이버 로드 실패");
		System.out.println(ce.getMessage());
	} catch (SQLException sqle) {
		System.out.println("SQL 연동 실패");
		System.out.println(sqle.getMessage());
	}
	
	String sql = "select id,pw from testuser";
	
	try {
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			dbid = rs.getString("id");
			dbpw = rs.getString("pw");
		}
		
		
	} catch (SQLException sqle) {
		System.out.println("SQL 연동 에러");
		System.out.println(sqle.getMessage());
	} finally {
		try {
			if(stmt!=null) stmt.close();
		} catch (Exception e) {}
	}
	
	if(id.equals(dbid)&&pw.equals(dbpw)){
		response.sendRedirect("login_success.jsp");
	}else{
		response.sendRedirect("login_fail.jsp");
	}

%>    