package banking.jdbc;

import java.util.Scanner;

import bank.my.test.Account;

public class AccManager_jdbc {
	private Account[] accounts; // 계좌를 저장할 배열
	private int numOfAccounts; // 현재 저장된 계좌 수
	private Scanner scan; // 사용자 입력용 스캐너

	// 생성자 : 배열 초기화, 계좌 수 0, Scanner 생성
	public AccManager_jdbc() {
		accounts = new Account[50]; // 최대 50개의 계좌 저장 가능
		numOfAccounts = 0;
		scan = new Scanner(System.in);
	}

	// 계좌 개설
	public void makeAccount() {
		System.out.println("계좌번호: ");
		String accNum = scan.nextLine();

		System.out.println("고객이름: ");
		String name = scan.nextLine();

		System.out.println("잔고: ");
		int balance = scan.nextInt();
		scan.nextLine(); // 입력 버퍼 제거

		accounts[numOfAccounts++] = new Account(accNum, name, balance);
		System.out.println("계좌개설 완료");
	}

	public void depositMoney() {
		System.out.println("입금할 계좌번호: ");
		String accNum = scan.nextLine();

		System.out.println("입금액: ");
		int amount = scan.nextInt();
		scan.nextLine(); // 버퍼 제거

		for (int i = 0; i < numOfAccounts; i++) {
			if (accounts[i].getAccNum().equals(accNum)) {
				accounts[i].deposit(amount);
				System.out.println("입금 완료");
				return;
			}
		}
		System.out.println("해당 계좌를 찾을 수 없습니다.");
	}

	public void withdrawMoney() {
		System.out.println("출금할 계좌번호: ");
		String accNum = scan.nextLine();

		System.out.println("출금액: ");
		int amount = scan.nextInt();
		scan.nextLine(); // 버퍼 제거

		for (int i = 0; i < numOfAccounts; i++) {
			if (accounts[i].getAccNum().equals(accNum)) {
				accounts[i].whitdraw(amount);
				return;
			}
		}
		System.out.println("해당 계좌를 찾을 수 없습니다.");
	}

	public void showAccInfo() {
		for (int i = 0; i < numOfAccounts; i++) {
			System.out.println(accounts[i]); // toString 자동 호출
		}
		System.out.println("전체계좌정보 출력 완료");
	}

}