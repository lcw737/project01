package banking.jdbc;

public interface IConnect {
	String ORACLE_DRIVER = ("oracle.jdbc.OracleDriver");
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//멤버메서드 
	void dbExecute();//쿼리문 실행
	void dbClose();//DB 자원 반납
	//사용자로부터 입력을 받기 위해 정의 
	String inputValue(String title);
	
	int MAKE = 1;
	int DEPOSIT = 2;
	int WITHDRAW = 3;
	int SELECTALL = 4;
	int SELECT = 5;
	int DELETE = 6;
	int EXIT = 7;

}
