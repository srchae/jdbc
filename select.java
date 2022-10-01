package jdbc0923;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test07_select {
	
	
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
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
			
			
			pstmt = con.prepareStatement(sql.toString());
			
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}finally {
			//자원반납(순서주의)
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
