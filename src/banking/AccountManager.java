package banking;

import java.util.HashSet;
import java.util.InputMismatchException;

public class AccountManager {
	
	
	private HashSet <Account> account;

	public AccountManager() {
	    account = new HashSet<>();
	}
	
	//계좌개설정보 입력
	public void makeAccount() {
	    System.out.println("***신규계좌개설***");
	    System.out.println("1. 보통계좌");
	    System.out.println("2. 신용신뢰계좌");
	    int kind = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();

	    System.out.print("계좌번호: ");
	    String bnum = BankingSystemMain.scan.nextLine();
	    System.out.print("고객이름: ");
	    String bname = BankingSystemMain.scan.nextLine();
	    System.out.print("잔고: ");
	    int balance = BankingSystemMain.scan.nextInt();
	    System.out.print("이자율(정수): ");
	    int interest = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();

	    Account Acc1 = null;
	    if (kind == 1) {
	        Acc1 = new NormalAccount(bname, bnum, balance, interest);
	    } else {
	        System.out.print("신용등급: ");
	        char grade = BankingSystemMain.scan.nextLine().charAt(0);
	        Acc1 = new HighCreditAccount(bname, bnum, balance, interest, grade);
	    }

	    if (account.contains(Acc1)) {
	        System.out.println("중복계좌발견됨. 덮어쓸까요? (y or n)");
	        String ans = BankingSystemMain.scan.nextLine();
	        if (ans.equalsIgnoreCase("y")) {
	            account.remove(Acc1);
	            account.add(Acc1);
	            System.out.println("기존 계좌를 덮어썼습니다.");
	        } else {
	            System.out.println("기존 계좌를 유지합니다.");
	        }
	    } else {
	        account.add(Acc1);
	        System.out.println("계좌개설 완료");
	    }
	}

	public void depositMoney() {
		
		String bnum ;
		int pMoney;
		
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.println("계좌번호:"); bnum = BankingSystemMain.scan.nextLine();
		System.out.println("입금액:"); pMoney = BankingSystemMain.scan.nextInt();
	
	try {	
		if(pMoney<0) {
			System.out.println("음수는 입금할 수 없습니다.");
			return;
		}
		 if (pMoney % 500 != 0) {
	            System.out.println("입금은 500원 단위로만 가능합니다.");
	            return;
	    }
		 for (Account acc : account) {
		        if (acc.bnum.equals(bnum)) {
		            int interest = 0;
		            if (acc instanceof HighCreditAccount) {
		                HighCreditAccount hca = (HighCreditAccount) acc;
		                interest = pMoney * hca.interest / 100 + pMoney * hca.ExtraInterest() / 100;
		            } else if (acc instanceof NormalAccount) {
		                NormalAccount nc = (NormalAccount) acc;
		                interest = pMoney * nc.interest / 100;
		            }
		            acc.balance += pMoney + interest;
		            System.out.println("입금이 완료되었습니다.");
		            return;
				}//if
			}//for
		}//try
		catch (InputMismatchException e) {
			System.out.println("숫자만 입력할 수 있습니다.");
	}//catch
}
	
	public void withdrawMoney() {
	   
		String bnum;
	    int mMoney;

	    System.out.println("***출 금***");
	    System.out.println("계좌번호와 출금할 금액을 입력하세요.");

	    try {
	        System.out.print("계좌번호: ");
	        bnum = BankingSystemMain.scan.nextLine();

	        System.out.print("출금액: ");
	        mMoney = BankingSystemMain.scan.nextInt();
	        BankingSystemMain.scan.nextLine(); 

	        if (mMoney <= 0) {
	            System.out.println("음수 또는 0원은 출금할 수 없습니다.");
	            return;
	        }

	        if (mMoney % 1000 != 0) {
	            System.out.println("출금은 1000원 단위로만 가능합니다.");
	            return;
	        }

	        for (Account acc : account) {
	            if (acc.bnum.equals(bnum)) {
	                if (mMoney > acc.balance) {
	                    System.out.println("잔고가 부족합니다. 금액전체를 출금할까요? (YES/NO)");
	                    String ans = BankingSystemMain.scan.nextLine();
	                    if (ans.equalsIgnoreCase("yes")) {
	                        System.out.printf("%d원을 출금합니다.%n", acc.balance);
	                        acc.balance = 0;
	                    } else {
	                        System.out.println("출금 요청이 취소되었습니다.");
	                    }
	                } else {
	                    acc.balance -= mMoney;
	                    System.out.println("출금이 완료되었습니다.");
	                }
	                return;
	            }//if
	        }//for
	        System.out.println("해당 계좌번호를 찾을 수 없습니다.");

	    }//try
	    catch (InputMismatchException e) {
	        System.out.println("출금액은 숫자만 입력할 수 있습니다.");
	        BankingSystemMain.scan.nextLine(); 
	    }
	}


	public void showAccInfo() {
	    for (Account acc : account) {
	        acc.showAccInfo1();
	    }
	    System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void deleteAccount() {
	    System.out.println("*** 계좌삭제 ***");
	    System.out.print("삭제할 계좌번호를 입력하세요: ");
	    String bnum = BankingSystemMain.scan.nextLine();

	    Account dummy = new Account("", bnum, 0); 
	    
	    if (account.remove(dummy)) {
	        System.out.println("계좌가 성공적으로 삭제되었습니다.");
	    } else {
	        System.out.println("해당 계좌를 찾을 수 없습니다.");
	    }
	}
}
