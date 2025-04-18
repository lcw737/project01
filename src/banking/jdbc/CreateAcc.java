package banking.jdbc;

import java.sql.SQLException;

public class CreateAcc extends bankingConnect {

    public CreateAcc() {
        super("education", "1234"); // 오라클 계정 정보
    }

    @Override
    public void dbExecute() {
        try {
            // 입력받기
            int accNum = Integer.parseInt(inputValue("계좌번호"));
            String name = inputValue("이름");
            int balance = Integer.parseInt(inputValue("초기입금액"));
            double interest = Double.parseDouble(inputValue("이자율(%)"));

            // 계좌번호 중복 확인
            String checkSql = "SELECT accnum FROM banking WHERE accnum = ?";
            psmt = con.prepareStatement(checkSql);
            psmt.setInt(1, accNum);
            rs = psmt.executeQuery();

            if (rs.next()) {
                System.out.println("이미 존재하는 계좌번호입니다.");
                return;
            }

            // INSERT 쿼리 준비
            String insertSql = "INSERT INTO banking (idx, accnum, name, balance, interest) " +
                               "VALUES (seq_banking_idx.NEXTVAL, ?, ?, ?, ?)";
            psmt = con.prepareStatement(insertSql);
            psmt.setInt(1, accNum);
            psmt.setString(2, name);
            psmt.setInt(3, balance);
            psmt.setDouble(4, interest);

            int result = psmt.executeUpdate();
            System.out.println(result > 0 ? "계좌가 성공적으로 개설되었습니다." : "계좌 개설 실패");

        } catch (SQLException e) {
            System.out.println("계좌 개설 중 SQL 예외 발생");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식이 잘못되었습니다.");
        } finally {
            dbClose();
        }
    }
}
