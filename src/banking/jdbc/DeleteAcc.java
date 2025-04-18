package banking.jdbc;

import java.sql.SQLException;
import java.sql.Types;

public class DeleteAcc extends bankingConnect{

	public DeleteAcc() {
		super("education", "1234");
	}
	
	String query;
	
	@Override
	public void dbExecute() {
	    try {
	        String accNum = inputValue("삭제할 계좌번호");

	        csmt = con.prepareCall("{ call DeleteAccount(?, ?) }");
	        csmt.setInt(1, Integer.parseInt(accNum)); // 숫자형 계좌번호
	        csmt.registerOutParameter(2, Types.VARCHAR);
	        csmt.execute();

	        System.out.println(csmt.getString(2));
	    }
	    catch (NumberFormatException e) {
	        System.out.println("계좌번호는 숫자만 입력해야 합니다.");
	    }
	    catch (SQLException e) {
	        System.out.println("쿼리 실행 중 예외 발생");
	        e.printStackTrace();
	    }
	    finally {
	        dbClose();
	    }
	}

}