package banking.jdbc;

import java.sql.SQLException;

public class WithdrawAcc extends bankingConnect {

    public WithdrawAcc() {
        super("education", "1234"); // 오라클 접속
    }

    @Override
    public void dbExecute() {
        try {
            // 1. 계좌번호 입력
            String accNum = inputValue("출금할 계좌번호");

            // 2. 계좌 정보 조회
            String sqlSelect = "SELECT balance FROM banking WHERE accnum = ?";
            psmt = con.prepareStatement(sqlSelect);
            psmt.setString(1, accNum);
            rs = psmt.executeQuery();

            if (!rs.next()) {
                System.out.println("해당 계좌번호가 존재하지 않습니다.");
                return;
            }

            int currentBalance = rs.getInt("balance");

            // 3. 출금 금액 입력
            System.out.print("출금 금액: ");
            int withdrawAmount = scan.nextInt();
            scan.nextLine(); // 개행 제거

            if (withdrawAmount <= 0) {
                System.out.println("0 이하의 금액은 출금할 수 없습니다.");
                return;
            }

            if (withdrawAmount > currentBalance) {
                System.out.println("잔액이 부족합니다. 현재 잔액: " + currentBalance + "원");
                return;
            }

            // 4. 출금 처리
            int newBalance = currentBalance - withdrawAmount;
            String sqlUpdate = "UPDATE banking SET balance = ? WHERE accnum = ?";
            psmt = con.prepareStatement(sqlUpdate);
            psmt.setInt(1, newBalance);
            psmt.setString(2, accNum);

            int result = psmt.executeUpdate();
            System.out.println(result > 0 ? "출금이 완료되었습니다." : "출금 실패");

        } catch (SQLException e) {
            System.out.println("출금 처리 중 SQL 예외 발생");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력하세요.");
        } finally {
            dbClose();
        }
    }
}
