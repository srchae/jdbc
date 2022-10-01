package jdbc0924;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.net.httpserver.Authenticator.Result;

public class Test03_selectCount {
	
	
	public static void main(String[] args) {
		//sungjuk 테이블 전체 행 조회하기
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	//select문을 실행한 결과(논리적 테이블)를 저장
		
		try {
			
			//DB 연결
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 서버 연결 성공 !!");
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) AS cnt FROM sungjuk ");
		
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				System.out.println("자료있음");
				System.out.println("전체 행 갯수 : " + rs.getInt(1));
				System.out.println("전체 행 갯수 : " + rs.getInt("cnt"));
				
			}else {
				System.out.println("자료없음~");
			}
			
			
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}finally {
			//자원반납(순서주의) 늦게 실행한 거 먼저 닫아줌
			try {
				if(rs != null) {rs.close();}
			}catch(Exception e) {}
			
			try {
				if(pstmt != null) {pstmt.close();}
			}catch (Exception e) {}
			
			try {
				if(con != null) {con.close();}
			}catch (Exception e) {}
		}//end
		
		System.out.println("END");
		
		
	}//main() end
}//class end
