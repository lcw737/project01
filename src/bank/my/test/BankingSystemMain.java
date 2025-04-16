package bank.my.test;

import java.util.Scanner;

import banking.AccountManager;

public class BankingSystemMain implements ICustomDefine {

	// 키보드 입력을 위한 인스턴스
	static Scanner scan = new Scanner(System.in);
	// 계좌정보 저장을 위한 인스턴스배열
	static Account[] accounts = new Account[50];

	public static void showMenu() {
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료");
	}

	public static void main(String[] args) {

		bank.my.test.AccountManager acm = new bank.my.test.AccountManager();

		while (true) {
			// 메뉴출력
			showMenu();
			System.out.print("메뉴입력:");
			int key = scan.nextInt();
			scan.nextLine();// 버퍼에 남은 엔터키 제거
			switch (key) {
			// 계좌개설
			case ICustomDefine.MAKE:
				acm.makeAccount();
				System.out.println("계좌개설");
				break;
			// 입금
			case ICustomDefine.DEPOSIT:
				acm.depositMoney();
				System.out.println("입금");
				break;
			// 출금
			case ICustomDefine.WITHDRAW:
				acm.withdrawMoney();
				System.out.println("출금");
				break;
			// 전체계좌정보출력
			case ICustomDefine.INQUIRE:
				acm.showAccInfo();
				System.out.println("전체계좌정보출력");
				break;
			// 프로그램종료
			case ICustomDefine.EXIT:
				System.exit(0);
				break;
			}// swhitch
		} // while
	}// main
}// class
