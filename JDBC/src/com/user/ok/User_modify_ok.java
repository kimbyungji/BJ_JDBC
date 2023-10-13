package com.user.ok;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/user_modify_ok")
public class User_modify_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_modify_ok() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 정보수정 페이지로 이동하기 위한 DB엣 ㅓ정보를 얻어오는 작업
		// DB에서 가져올 정보는 session에서 id값을 얻어서 사용
		HttpSession session = request.getSession();	// 세션 생성
		String id = (String)session.getAttribute("user_id");
		
//		// DB연동을 위한 변수 및 객체 선언
//		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String user = "mytest";
//		String password = "mytest";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		// DB에 전달할 SQL 작성
//		String sql = "select * from testuser where id = ?";
//		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id); // id는 세션에서 얻음
//			rs = pstmt.executeQuery();
//			
//			/*
//			 *  rs.next() 결과값을 조회
//			 *  rs.getString(컬럼명)을 통해서 값을 얻어옴.. name, phone1, phone2, gender
//			 *  request 강제 저장한 후에 포워드 처리 user_update.jsp전달
//			 */
//			if(rs.next()) {
//				String name = rs.getString("name");
//				String pw = rs.getString("pw");
//				String phone1 = rs.getString("phone1");
//				String phone2 = rs.getString("phone2");
//				String gender = rs.getString("gender");
//				
//				// request에 강제 저장 및 포워드
//				request.setAttribute("user_name", name);
//				request.setAttribute("user_pw", pw);
//				request.setAttribute("user_phone1", phone1);
//				request.setAttribute("user_phone2", phone2);
//				request.setAttribute("user_gender", gender);
//				
//				// 검증
////				System.out.print(id+pw+phone1+phone2+gender);
//				
//				// 포워드
//				request.getRequestDispatcher("user_update.jsp").forward(request, response);
//			}
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(conn!=null) conn.close();
//				if(pstmt!=null) conn.close();
//				if(rs!=null) rs.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO vo = dao.getInfo(id);
		
		request.setAttribute("user_vo",vo);
		
		request.getRequestDispatcher("user_update.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}

}
