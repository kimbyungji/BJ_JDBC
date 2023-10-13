<%@page import="com.user.ok.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// id는 세션을 통해서 얻움
	String id = (String)session.getAttribute("user_id");
	// 회원 정보는 request를 통해서 얻음
	/*
	String pw = (String)request.getAttribute("user_pw");
	String name = (String)request.getAttribute("user_name");
	String phone1 = (String)request.getAttribute("user_phone1");
	String phone2 = (String)request.getAttribute("user_phone2");
	String gender = (String)request.getAttribute("user_gender");
	*/
	MemberVO vo = (MemberVO)request.getAttribute("user_vo");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>업데이트 페이지(user_update.jsp)</title>
	</head>
	<body>
		<h2>회워정보 수정 페이지</h2>
		<form action="user_update_ok" method="post">
			ID : <input type="text" name="id" size="10" value="<%=id%>" readonly><br>
			PW : <input type="password" name="pw" size="10" value="<%=vo.getPw() %>" readonly><br>
			이름 : <input type="text" name="name" size="10" value="<%=vo.getName() %>"><br>
			전화번호 :
			<!-- 
				radio버튼이나, checkbox를 미리 선택하는 기능을 checked,
				radio버튼이나, checkbox를 선택할 수 없게 막는 기능을 disabled,
				select 태그에서 미리 선택하는 속성값은 selected,
				input 태그에서 읽기만 허용하는 속성은 readonly
			 -->
			<select name="phone1">			
				<option <%=vo.getPhone1().equals("010")?"selected":"" %>>010</option>	
				<option <%=vo.getPhone1().equals("011")?"selected":"" %>>011</option>
				<option <%=vo.getPhone1().equals("018")?"selected":"" %>>018</option>
			</select>
			- <input type="text" name="phone2" value="<%=vo.getPhone2() %>"><br>
			<br>
			성별 :
			<input type="radio" name="gender" value='m' <%=vo.getGender().equals("m")?"checked":"" %>>남자
			<input type="radio" name="gender" value='w' <%=vo.getGender().equals("w")?"checked":"" %>>여자
			<br>
			<input type="submit" value="수정">
		</form>
	</body>
</html>