package jdbc0923;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test04_delete {

	public static void main(String[] args) {
		//sungjuk 테이블 행 삭제 연습
		
		try {
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 연결 성공 !!");
			
			//총점(tot), 평균(aver) 구하기
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM sungjuk ");
			
			
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			
			int result = pstmt.executeUpdate();		//insert, update, delete문 실행
			if(result == 0) {
				System.out.println("행 삭제 실패 !!");
			}else {
				System.out.println("행 삭제 성공~~");
			}//if end
			
			//자원반납 (순서주의)
			pstmt.close();
			con.close();
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}//end
		
		System.out.println("END");
		
		
	}//main() end
}//class end
