package jdbc0923;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test05_insert {

	public static void main(String[] args) {
		//변수를 이용해서 sungjuk 테이블에 행 추가 연습
		try {
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 연결 성공 !!");
			
			
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)");
			sql.append(" VALUES (sungjuk_seq.nextval, ?, ?, ?, ?, ?, sysdate)");
			//-> ? 특정값으로 대입할 수 있는 표식
			// 물음표의 갯수와 몇 번째 물음표인지 인식하고 있어야 함
			
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			//-> ?의 갯수와 값의 갯수, 순서, 자료형이 일치해야 한다
			
			pstmt.setString(1, "김연아"); 	//1 -> 첫번째 물음표, uname 칼럼
			pstmt.setInt(2, 85);			//2 -> 두번째 물음표, kor 칼럼
			pstmt.setInt(3, 60);			//3 -> 세번째 물음표, eng 칼럼
			pstmt.setInt(4, 90);			//4 -> 네번째 물음표, mat 칼럼
			pstmt.setString(5, "Seoul");		//5 -> 다섯번째 물음표, addr 칼럼
			
			
			
			int result = pstmt.executeUpdate();		//insert, update, delete문 실행
			if(result == 0) {
				System.out.println("행 삭제 실패 !!");
			}else {
				System.out.println("행 삭제 성공~~");
			}//if end
			
			
			
			pstmt.close();
			con.close();
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}//end
		
		System.out.println("END");
		
		
	}//main() end
}//class end
