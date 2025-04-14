package banking;

public class AccountManager {
	
	
	private Account[] account;
	private int numOfAccount;

	public AccountManager(int num) {
		account = new Account[num];
		numOfAccount = 0;
	}

	//계좌개설정보 입력
	public void makeAccount() {
	    System.out.println("***신규계좌개설***");
	    System.out.println("1.보통계좌");
	    System.out.println("2.신용계좌");
	    int kind = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();
	    System.out.print("계좌번호: ");
	    String bnum = BankingSystemMain.scan.nextLine();
	    System.out.print("고객이름: ");
	    String bname = BankingSystemMain.scan.nextLine();
	    System.out.print("잔고: ");
	    int balance = BankingSystemMain.scan.nextInt();
	    System.out.print("이자율: ");
	    int interest = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();
	    if (kind == 1) {
	        account[numOfAccount++] = 
	        		new NormalAccount(bname, bnum, balance, interest);
	    } 
	    else {
	        System.out.print("신용등급: ");
	        char grade = BankingSystemMain.scan.nextLine().charAt(0);
	        account[numOfAccount++] = 
	        		new HighCreditAccount(bname, bnum, balance, interest, grade);
	    }
	    System.out.println("계좌개설 완료");
	}
	public void depositMoney() {
		String bnum ;
		int pMoney;
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.println("계좌번호:"); bnum = BankingSystemMain.scan.nextLine();
		System.out.println("입금액:"); pMoney = BankingSystemMain.scan.nextInt();
		for (int i = 0; i < numOfAccount; i++) {
		    if (account[i].bnum.equals(bnum)) {
		        int interest = 0;

		    if (account[i] instanceof HighCreditAccount) {
		    	HighCreditAccount pm = (HighCreditAccount) account[i];
		        interest = pm.balance * pm.interest / 100;
		        interest += pm.balance * pm.ExtraInterest() / 100;
		    } 
		    else if (account[i] instanceof NormalAccount) {
		    	NormalAccount pm = (NormalAccount) account[i];
		    	interest = pm.balance * pm.interest / 100;
		    }
		    account[i].balance += interest + pMoney;
		    System.out.println("입금이 완료되었습니다.");
		    return;
		        }
		    }
		}
	
	public void withdrawMoney() {
		String bnum ;
		int mMoney;
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.println("계좌번호:"); bnum = BankingSystemMain.scan.nextLine();
		System.out.println("출금액:"); mMoney = BankingSystemMain.scan.nextInt();
		for (int i = 0; i < numOfAccount; i++) {
	        if (account[i].bnum.equals(bnum)) {
	            account[i].balance -= mMoney;
	            System.out.println("출금이 완료되었습니다.");
	            return;
	        }
	    }
	}

	public void showAccInfo() {
		for(int i=0; i<numOfAccount;i++) {
			account[i].showAccInfo1(); 
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}


}
