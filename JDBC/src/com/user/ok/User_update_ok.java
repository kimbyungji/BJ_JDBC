package com.user.ok;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user_update_ok")
public class User_update_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;      
 
    public User_update_ok() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Form데이터 처리
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String gender = request.getParameter("gender");
		
//		System.out.println(id+pw+name+phone1+phone2+gender);
		
//		// DB연동을 위한 변수 선언
//		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String user = "mytest";
//		String password = "mytest";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		String sql = "update testuser set pw=?, name=?, phone1=?, phone2=?, gender=? where id=?";
//		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, pw);
//			pstmt.setString(2, name);
//			pstmt.setString(3, phone1);
//			pstmt.setString(4, phone2);
//			pstmt.setString(5, gender);
//			pstmt.setString(6, id);
//			
//			int result = pstmt.executeUpdate();
//			
//			if(result ==1) {
//				// 수정 성공
//				PrintWriter out = response.getWriter();
//				out.println("<script>"+"alert('success');"+"location.href='user_my_page.jsp'"+"</script>");
////				response.sendRedirect("user_my_page.jsp");
//			}else {
//				// 수정 실패
//				PrintWriter out = response.getWriter();
//				out.println("<script>"+"alert('fail')"+"history.go(-1);"+"</script>");
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
		// 수정
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO vo = new MemberVO(id, pw, name, phone1, phone2, gender);
		int result = dao.update(vo);
		
		if(result ==1) {
			response.sendRedirect("user_my_page.jsp");
			System.out.println("성공");
		}
	}

}
