package banking.jdbc;

import java.sql.SQLException;

public class DepositAcc extends bankingConnect {

    public DepositAcc() {
        super("education", "1234"); // Oracle 계정
    }

    @Override
    public void dbExecute() {
        try {
            // 1. 계좌번호 입력
            String accNum = inputValue("입금할 계좌번호");

            // 2. 계좌정보 조회
            String sqlSelect = "SELECT balance, interest FROM banking WHERE accnum = ?";
            psmt = con.prepareStatement(sqlSelect);
            psmt.setString(1, accNum);
            rs = psmt.executeQuery();

            if (!rs.next()) {
                System.out.println("해당 계좌번호가 존재하지 않습니다.");
                return;
            }

            int balance = rs.getInt("balance");
            double interest = rs.getDouble("interest");

            // 3. 입금금액 입력
            System.out.print("입금 금액: ");
            int depositAmount = scan.nextInt();
            scan.nextLine(); // 개행 제거

            if (depositAmount <= 0) {
                System.out.println("0 이하의 금액은 입금할 수 없습니다.");
                return;
            }

            // 4. 이자 포함 계산
            int interestAmount = (int) (depositAmount * (interest / 100));
            int newBalance = balance + depositAmount + interestAmount;

            // 5. UPDATE 처리
            String sqlUpdate = "UPDATE banking SET balance = ? WHERE accnum = ?";
            psmt = con.prepareStatement(sqlUpdate);
            psmt.setInt(1, newBalance);
            psmt.setString(2, accNum);

            int result = psmt.executeUpdate();
            System.out.println(result > 0 ? "입금이 완료되었습니다." : "입금 실패");

        } catch (SQLException e) {
            System.out.println("입금 처리 중 SQL 예외 발생");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력하세요.");
        } finally {
            dbClose();
        }
    }
}
