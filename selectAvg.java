package jdbc0924;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.net.httpserver.Authenticator.Result;

public class Test04_selectAvg {
	
	
	public static void main(String[] args) {
		//문제) 주소가 서울인 행들의 국영수 평균값을 구하시오
		//		(단, 소수점은 반올림해서 둘째자리 값까지)
		String addr = "Seoul";
		
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
			sql.append("SELECT ROUND(AVG(kor),2) as avg_kor ");
			sql.append("	   ,ROUND(AVG(eng),2) as avg_eng ");
			sql.append("	   ,ROUND(AVG(mat),2) as avg_mat ");
			sql.append(" FROM sungjuk ");
			sql.append(" WHERE addr=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, addr);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				System.out.println("자료있음");
				//1)칼럼 순서로 접근
				System.out.printf("국어평균 : %.2f\n", rs.getDouble(1));
				System.out.printf("영어평균 : %.2f\n", rs.getDouble(2));
				System.out.printf("수학평균 : %.2f\n", rs.getDouble(3));
				
				System.out.println("---------------------");
				
				//2)칼럼명으로 접근
				System.out.printf("국어평균 : %.2f\n", rs.getDouble("avg_kor"));
				System.out.printf("영어평균 : %.2f\n", rs.getDouble("avg_eng"));
				System.out.printf("수학평균 : %.2f\n", rs.getDouble("avg_mat"));
				
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
