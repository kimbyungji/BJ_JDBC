package com.user.ok;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user_join_ok")
public class User_join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_join_ok() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Form으로 들어온 값 처리(post)
		request.setCharacterEncoding("utf-8"); //인코딩
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String gender = request.getParameter("gender");
		
		//System.out.println(id + ", "+pw+ ", "+name+ ", "+phone1+ ", "+phone2+ ", "+gender);
		
//		//DB와 연동에 필요한 변수를 선언
//		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String user = "mytest";
//		String password = "mytest";
//		
//		Connection conn = null;
//		Statement stmt = null;
//		
//		//DB에 전달할 SQL문 작성
//		String sql = "INSERT INTO testuser " +
//	             "VALUES('" + id + "','" + pw + "','" + name + "','" + phone1 + "','" + phone2 + "','" + gender + "')";
//
//		try {
//			
//			//1.드라이버 로드
//			Class.forName(driver);
//			//2.Connection 객체 생성
//			conn = DriverManager.getConnection(url,user,password);
//			//3.sql 전달 객체 생성
//			stmt = conn.createStatement();
//			//4.SQL 객체에 sql 실행
//			int result = stmt.executeUpdate(sql);
//			
//			if(result == 1) {
//				//추가 성공
//				response.sendRedirect("join_success.jsp");
//			}else {
//				response.sendRedirect("join_fail.jsp");
//			}					
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn != null) conn.close();
//				if(stmt != null) stmt.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		// DAO 객체 생성
		MemberDAO dao = MemberDAO.getInstance();	// 싱글톤 가져오는 법
		
		// MemberVO 객체 생성
		MemberVO vo = new MemberVO(id, pw, name, phone1, phone2, gender);
		
		int result = dao.join(vo);
		if(result ==1) {	// 추가 성공
			response.sendRedirect("join_success.jsp");
		}else {		// 추가 실패
			response.sendRedirect("join_fail.jsp");
		}
		
	}

}
