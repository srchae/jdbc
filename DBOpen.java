package jdbc0923;

import java.sql.DriverManager;

public class Test01_DBOpen {

	public static void main(String[] args) {
		//JDBC(Java Database Connection)
		//자바와 오라클DB를 연동
		//자바에서 외부 응용프로그램(Oracle DB, Maria DB)을 연결하려며 드라이버가 있어야 함.
		//오라클 DB 연결 드라이버는 해당 사이트에서 다운 받을 수 있다
		//만일, 오라클 DB 서버가 설치되어 있다면 오라클 DB 설치 폴더에 내장되어 있음
		
		//오라클 DB 연결 드라이버 저장 경로
		//C:/app/user/product/18.0.0/dbhomeXE/jdbc/lib
		//ojdbc8.jar 복사
		
		/*
        [basic02_jdbc프로젝트와 OracleDB 드라이버와 연결]
                basic02_jdbc프로젝트 우클릭 -> Build Path
                                   -> Configure Build Path...
                                   -> Libraries 
                                   -> Classpath
                                   -> Add External JARs...-> ojdbc8.jar 선택
		*/
		
		/*
		  	- 네트워크에서 자신의 PC를 가리키는 주소 : localhost, 127.0.0.1
            - port번호 : 서버에서 특정 데이터가 입출력하는 전용문. 0~65535
                        예) 	Oracle DB : 1521
                           	MySQL DB : 3306 
		 */
		
		try {
			
			//1) 오라클 DB 서버 연결 관련 정보
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="1234";
			String driver="oracle.jdbc.driver.OracleDriver";	//ojdbc8.jar
			
			//2) 드라이버 로딩
			Class.forName(driver);
			
			
			//3) 오라클DB 서버 연결
			DriverManager.getConnection(url, user, password);
			
			System.out.println("오라클 DB 연결 성공 !!");
			
			
		}catch (Exception e) {
			System.out.println("오라클DB 연결실패:" + e);
		}//end
		
	}//main() end
}//class end
