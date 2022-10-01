package jdbc0924;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.net.httpserver.Authenticator.Result;

public class Test01_selectOne {
	
	
	public static void main(String[] args) {
		
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
			sql.append("SELECT sno, uname, kor, eng, mat, tot, aver, addr, wdate ");
			sql.append(" FROM sungjuk ");
			sql.append(" WHERE sno=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, 74); 	//sno=74번 가져오기
			
			//select문 실행
			rs = pstmt.executeQuery();
			// cursor : 펜을 가리키는 값. 이동할 수 있다.
			if (rs.next()) {	//cursor가 있나요?
				System.out.println("자료있음");
				//1)칼럼순서로 접근
				//->select 칼럼1, 칼럼2, 칼럼3 ~~
				//->자료형을 일치시키면서 가져온다
				System.out.println(rs.getInt(1));  	 //1번 칼럼, sno 칼럼
				System.out.println(rs.getString(2)); //2번 칼럼, uname 칼럼
				System.out.println(rs.getInt(3));	 
				System.out.println(rs.getInt(4));
				System.out.println(rs.getInt(5));
				System.out.println(rs.getInt(6));
				System.out.println(rs.getInt(7));
				System.out.println(rs.getString(8));
				System.out.println(rs.getString(9));
				
				//2)칼럼명으로 접근
				//->select sno, uname, kor ~~	
				System.out.println(rs.getInt("sno"));
				System.out.println(rs.getString("uname")); //2번 칼럼, uname 칼럼
				System.out.println(rs.getInt("kor"));	 
				System.out.println(rs.getInt("eng"));
				System.out.println(rs.getInt("mat"));
				System.out.println(rs.getInt("tot"));
				System.out.println(rs.getInt("aver"));
				System.out.println(rs.getString("addr"));
				System.out.println(rs.getString("wdate"));
				
				
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
