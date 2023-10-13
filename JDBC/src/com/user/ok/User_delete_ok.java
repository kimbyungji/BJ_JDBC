package com.user.ok;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/user_delete_ok")
public class User_delete_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public User_delete_ok() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * sql -> delete from 테이블명 where id = ?;
		 * 
		 * 1. 아이디는 세션에서 얻는다.
		 * 2. pstmt를 이용하여 삭제 진행
		 * 3. excuteUpdate() 메서드 실행 후 반호나 값 확인
		 * 		1이면 성공. 세션 전부 삭제 후 user_login.jsp로 이동
		 * 		0이면 실패. user_my_page.jsp로 이동
		 */
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
//		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String user = "mytest";
//		String password = "mytest";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		String sql = "delete from testuser where id = ?";
//		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			int result = pstmt.executeUpdate();
//			
//			if(result==1) {
//				session.invalidate();
//				response.sendRedirect("user_login.jsp");
//			}else {
//				response.sendRedirect("user_my_page.jsp");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(conn!=null) conn.close();
//				if(pstmt!=null) pstmt.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.delete(id);
		
		if(result ==1) {
			session.invalidate();
			response.sendRedirect("user_login.jsp");
		}else {
			response.sendRedirect("user_my_page.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
