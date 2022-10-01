package jdbc0923;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test02_insert {

	public static void main(String[] args) {
		//sungjuk 테이블 행 추가 연습
		
		try {
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 연결 성공 !!");
			
			//4) SQL 작성
			//->SQL 종결문자 ;를 쓰면 오류남
			StringBuilder sql = new StringBuilder();
			sql.append("insert into sungjuk(sno, uname, kor, eng, mat, addr, wdate)");
			sql.append("values (sungjuk_seq.nextval, '김연아', 99, 98, 87, 'Seoul', sysdate)");
			//System.out.println(sql.toString());
			
			//5) SQL 형식으로 변환
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			
			
			//6) SQL문 실행
			int result = pstmt.executeUpdate();		//insert, update, delete문 실행
			System.out.println("실행결과 : " + result);
			
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}//end
		
	}//main() end
}//class end
