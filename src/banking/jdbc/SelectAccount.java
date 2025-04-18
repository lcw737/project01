package banking.jdbc;

import java.sql.SQLException;

public class SelectAccount extends bankingConnect {

    public SelectAccount() {
        super("education", "1234");
    }

    @Override
    public void dbExecute() {
        try {
            String sql = "SELECT idx, accnum, name, balance, interest FROM banking WHERE accnum = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, inputValue("찾는 계좌번호"));
            rs = psmt.executeQuery();

            if (rs.next()) {
                System.out.printf("일련번호: %s\n계좌번호: %s\n이름: %s\n잔액: %,d원\n이자율: %d%%\n",
                        rs.getString("idx"),
                        rs.getString("accnum"),
                        rs.getString("name"),
                        rs.getInt("balance"),
                        rs.getInt("interest"));
            } else {
                System.out.println("해당 계좌가 존재하지 않습니다.");
            }

        } catch (SQLException e) {
            System.out.println("쿼리 실행 중 예외 발생");
            e.printStackTrace();
        } finally {
            dbClose();
        }
    }

    public static void main(String[] args) {
        new SelectAccount().dbExecute();
    }
}
