package com.user.ok;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.util.JdbcUtil;

public class MemberDAO {
	
	/*
	 * DAO는 단수 DB연동을 담당하는 클래스
	 * * 단수?DB연동?(Singleton) -> DB에 하나의 연결을 통해서 서비스를 구현
	 * 	why? DB연동을 통한 통신시 데이터 교환이 많이 일어남
	 * 여러개 생성하도록 일반 클래스로 만들면, 메모리 과부하가 올 수 있다.
	 * 그래서 싱글톤 패턴을 적용하여 객체를 1개로 제한 
	 */
	
	// 1. 스스로의 객체를 멤버변수로 선언하고 1개로 제한
	private static MemberDAO instance = new MemberDAO();
	
	// Connection Pool을 위한 객체 생성
	private DataSource ds; // 데이터 소스 객체 생성
	private Context ct;	// javax.naming.*
	
	// 2. 외부에서 객체를 생성할 수 없도록 생성자에 private 처리
	public MemberDAO() {
		// 생성자가 한번 동작할 때 다음과 같은 내용을 처리
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("드라이버 로드 에러");
		}
		
		// 커넥션 풀을 위한 생성
		try {
			ct = new InitialContext(); // 이니셜 컨텍스트 객체 생성
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");	// 이니셜컨덱스트로부터 찾기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 3. 외부에서 객체를 요구할 때 제공할 getter 메서드를 생성
	public static MemberDAO getInstance() {
		return instance;
	}		
	
	// --------------- 중복되는 코드를 멤버 변수로 선언 -------------------
	private String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul";
	private String user = "mytest";
	private String password = "mytest";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//------------------ DB와 연동하는 기능들을 메서드로 선언 ----------------
	
	// 회원 가입 메서드
	public int join(MemberVO vo) {
		int result = 0;	// 결과 반환
		
		String sql = "insert into testuser values(?,?,?,?,?,?)";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
//			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getGender());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	// 로그인 메서드
	public int login(String id, String pw) {
		int result = 0;
		
		String sql = "select * from testuser where id = ? and pw = ?";
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 로그인 성공
				result = 1;
			}else {	//로그인 실패
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	// 회워 정보 조회하는 메서드
	public MemberVO getInfo(String id) {
		MemberVO vo = null;
		
		String sql = "select * from testuser where id = ?";
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// rs.getString(컬럼)을 통해서 값을 얻기
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone1 = rs.getString("phone1");
				String phone2 = rs.getString("phone2");
				String gender = rs.getString("gender");
				
				vo = new MemberVO(id, pw, name, phone1, phone2, gender);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	public int update(MemberVO vo) {
		int result = 0;
		
		String sql = "update testuser set pw=?, name=?, phone1=?, phone2=?, gender=? where id=?";
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone1());
			pstmt.setString(4, vo.getPhone2());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}
	
	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from testuser where id= ?";
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}
}
