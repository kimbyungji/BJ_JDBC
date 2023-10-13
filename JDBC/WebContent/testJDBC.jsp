<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>testJDBC.jsp</title>
	</head>
	<body>
		<%
			// JDBC처리를 위한 객체 확인 Connection, PreparedStatement, ResultSet
			Connection conn = null;
			String	 driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul";
			String user = "root";
			String password = "root1234";
			
			boolean connect = false;
			
			try{
				// 1. Driver 로드
				Class.forName(driver);
				
				// 2. DB연결
				conn = DriverManager.getConnection(url, user, password);
				connect = true;
				
			}catch(ClassNotFoundException cnfe){
				connect = false;
				System.out.println("DB드라이버 로딩 실패 : "+cnfe.toString());
			}catch(SQLException sqle){
				connect = false;
				System.out.println("DB 접속 실패 : "+sqle.toString());
			}
			if(connect){
				System.out.println("연결");
			}else{
				System.out.println("연결X");
			}
		%>
	</body>
</html>