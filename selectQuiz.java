package jdbc0924;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.net.httpserver.Authenticator.Result;

public class Test06_selectQuiz {
	
	
	public static void main(String[] args) {
		//문제) 학번 g1001이 수강신청한 과목을 과목코드별로 조회하시오
        /*     g1001  p001  OOP
               g1001  p003  JSP  
               g1001  d001  웹표준      */
		
		String hakno = "g1001";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		
		try {
			
			//DB 연결
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 서버 연결 성공 !!");
			
			
			/*
			select SU.*, GW.gname
			from tb_sugang SU join tb_gwamok GW
			on SU.gcode = GW.gcode
			where hakno = 'g1001'
			order by SU.gcode;
			*/
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT SU.hakno, SU.gcode, GW.gname ");
			sql.append(" FROM tb_sugang SU JOIN tb_gwamok GW ");
			sql.append(" ON SU.gcode = GW.gcode ");
			sql.append(" WHERE SU.hakno = ? "); 	//변수처리 하는 곳을 물음표로
			sql.append(" ORDER BY SU.gcode ");
			
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, hakno);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				System.out.println("자료 있음~~");
				do {
					System.out.print(rs.getString("hakno") + " ");
					System.out.print(rs.getString("gcode") + " ");
					System.out.print(rs.getString("gname") + " ");	 
					System.out.println();
					
				}while(rs.next()); //다음 cursor가 있는지?
			}else {
				System.out.println("자료없음!!");
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
