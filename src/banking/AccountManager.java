package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.InputMismatchException;

public class AccountManager implements Serializable {

	private HashSet<Account> account;

	private AutoSaver autoSaverThread;

	public AccountManager() {
		account = new HashSet<>();
	}

	// 계좌개설정보 입력
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

	// 입금
	public void depositMoney() {
	    System.out.println("***입 금***");
	    System.out.println("계좌번호와 입금할 금액을 입력하세요.");
	    System.out.print("계좌번호: ");
	    String bnum = BankingSystemMain.scan.nextLine();
	    System.out.print("입금액: ");
	    int pMoney = BankingSystemMain.scan.nextInt();
	    BankingSystemMain.scan.nextLine();

	    try {
	        if (pMoney < 0) {
	            System.out.println("음수는 입금할 수 없습니다.");
	            return;
	        }
	        if (pMoney % 500 != 0) {
	            System.out.println("입금은 500원 단위로만 가능합니다.");
	            return;
	        }

	        for (Account acc : account) {
	            if (acc.getBnum().equals(bnum)) {
	                int interest = 0;

	                String type = acc.getAccountType();
	                if (type.equals("신용계좌")) {
	                    HighCreditAccount hca = (HighCreditAccount) acc;
	                    interest = pMoney * hca.getInterest() / 100 + pMoney * hca.ExtraInterest() / 100;
	                } else if (type.equals("보통계좌")) {
	                    NormalAccount nc = (NormalAccount) acc;
	                    interest = pMoney * nc.getInterest() / 100;
	                }

	                acc.setBalance(acc.getBalance() + pMoney + interest);
	                System.out.println("입금이 완료되었습니다.");
	                return;
	            }
	        }

	        System.out.println("해당 계좌번호를 찾을 수 없습니다.");
	    } catch (InputMismatchException e) {
	        System.out.println("숫자만 입력할 수 있습니다.");
	        BankingSystemMain.scan.nextLine();
	    }
	}


	// 출금
	public void withdrawMoney() {
	    System.out.println("***출 금***");
	    System.out.println("계좌번호와 출금할 금액을 입력하세요.");

	    try {
	        System.out.print("계좌번호: ");
	        String bnum = BankingSystemMain.scan.nextLine();

	        System.out.print("출금액: ");
	        int mMoney = BankingSystemMain.scan.nextInt();
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
	            if (acc.getBnum().equals(bnum)) {
	                if (mMoney > acc.getBalance()) {
	                    System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요? (YES/NO)");
	                    String ans = BankingSystemMain.scan.nextLine();
	                    if (ans.equalsIgnoreCase("yes")) {
	                        System.out.printf("%d원을 출금합니다.%n", acc.getBalance());
	                        acc.setBalance(0);
	                    } else {
	                        System.out.println("출금 요청이 취소되었습니다.");
	                    }
	                } else {
	                    acc.setBalance(acc.getBalance() - mMoney);
	                    System.out.println("출금이 완료되었습니다.");
	                }
	                return;
	            }
	        }

	        System.out.println("해당 계좌번호를 찾을 수 없습니다.");
	    } catch (InputMismatchException e) {
	        System.out.println("출금액은 숫자만 입력할 수 있습니다.");
	        BankingSystemMain.scan.nextLine();
	    }
	}


	// 전체계좌정보
	public void showAccInfo() {
		for (Account acc : account) {
			acc.showAccInfo1();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	// 계좌삭제
	public void deleteAccount() {
	    System.out.println("*** 계좌삭제 ***");
	    System.out.print("삭제할 계좌번호를 입력하세요: ");
	    String bnum = BankingSystemMain.scan.nextLine();

	    Account target = null;

	    for (Account acc : account) {
	        if (acc.getBnum().equals(bnum)) {
	            target = acc;
	            break;
	        }
	    }

	    if (target != null) {
	        account.remove(target);
	        System.out.println("계좌가 성공적으로 삭제되었습니다.");
	    } else {
	        System.out.println("해당 계좌를 찾을 수 없습니다.");
	    }
	}


	// IO적용 직렬화
	public void OutputObject() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("AccountInfo.obj"));

			out.writeObject(account);

			out.close();
			System.out.println("AccountInfo.obj 로 저장되었습니다.");

		} catch (IOException e) {
			System.out.println("저장 중 오류발생");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void LoadObject() {

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("AccountInfo.obj"));

			account = (HashSet<Account>) in.readObject();
			in.close();
			System.out.println("AccountInfo.obj 를 불러옵니다. 총 계좌 수:" + account.size());
		} catch (FileNotFoundException e) {
			System.out.println("저장된 계좌 정보가 없습니다. 새로 시작합니다.");
		} catch (IOException e) {
			System.out.println("예외발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 자동저장
	public void manageAutoSave() {
		System.out.println("1. 자동저장 ON");
		System.out.println("2. 자동저장 OFF");

		int opt = BankingSystemMain.scan.nextInt();
		BankingSystemMain.scan.nextLine();

		switch (opt) {
		case 1:
			if (autoSaverThread != null && autoSaverThread.isAlive()) {
				System.out.println("이미 자동저장이 실행 중입니다.");
			} else {
				autoSaverThread = new AutoSaver(account); // account는 HashSet<Account>
				autoSaverThread.start();
				System.out.println("자동저장 시작됨");
			}
			break;

		case 2:
			if (autoSaverThread != null && autoSaverThread.isAlive()) {
				autoSaverThread.interrupt();
				System.out.println("자동저장 중단됨");
			} else {
				System.out.println("자동저장이 실행 중이지 않습니다.");
			}
			break;

		default:
			System.out.println("잘못된 입력입니다.");
		}

	}
}
