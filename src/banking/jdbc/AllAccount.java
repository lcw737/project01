package banking.jdbc;

import java.sql.SQLException;

public class AllAccount extends bankingConnect {

    public AllAccount() {
        super("education", "1234");
    }

    @Override
    public void dbExecute() {
        try {
            String sql = "SELECT accnum, name, balance, interest FROM banking ORDER BY idx";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("전체 계좌 목록");
            System.out.println("────────────────────────────");
            int count = 0;
            while (rs.next()) {
                System.out.printf("계좌번호: %d\n이름: %s\n잔액: %,d원\n이자율: %.2f%%\n",
                    rs.getInt("accnum"),
                    rs.getString("name"),
                    rs.getInt("balance"),
                    rs.getDouble("interest"));
                System.out.println("────────────────────────────");
                count++;
            }

            if (count == 0) {
                System.out.println("등록된 계좌가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("전체 계좌 조회 중 SQL 예외 발생");
            e.printStackTrace();
        } finally {
            dbClose();
        }
    }
}
