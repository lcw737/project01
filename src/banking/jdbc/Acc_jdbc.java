package banking.jdbc;

public class Acc_jdbc {
	// 계좌번호(String형), 이름(String형), 잔액(int형)
		private String accNum;
		private String name;
		private int balance;

		// 생성자
		public Acc_jdbc(String accNum, String name, int balance) {
			super();
			this.accNum = accNum;
			this.name = name;
			this.balance = balance;
		}

		// 입금
		public void deposit(int amount) {
			balance += amount;
		}

		// 출금
		public void whitdraw(int amount) {
			balance -= amount;
		}

		// 게터/세터
		public String getAccNum() {
			return accNum;
		}

		public void setAccNum(String accNum) {
			this.accNum = accNum;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}

		// toString 오버라이딩
		@Override
		public String toString() {
			return "Account [계좌번호=" + accNum + ", 이름=" + name + ", 잔고=" + balance + "]";
		}

		public void showAccInfo() {
			System.out.println("계좌번호: " + accNum);
			System.out.println("고객이름: " + name);
			System.out.println("잔고: " + balance);
			System.out.println("-------------------------");
		}

	}