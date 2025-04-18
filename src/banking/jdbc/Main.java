package banking.jdbc;

import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static void showMenu() {// 메뉴출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입  금");
		System.out.println("3.출  금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.지정계좌정보출력");
		System.out.println("6.계좌정보삭제");
		System.out.println("7.프로그램종료");
		System.out.println("---------------");
	}
	
	public static void main(String[] args) {
		while(true) {
			showMenu();
			System.out.print("메뉴선택:");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case IConnect.MAKE:
				new CreateAcc().dbExecute();
				break;
			case IConnect.DEPOSIT:
				new DepositAcc().dbExecute();
				break;
			case IConnect.WITHDRAW:
				new WithdrawAcc().dbExecute();
				break;
			case IConnect.SELECTALL:
				new AllAccount().dbExecute();
				break;
			case IConnect.SELECT:
				new SelectAccount().dbExecute();
				break;
			case IConnect.DELETE:
				new DeleteAcc().dbExecute();
				break;
			case IConnect.EXIT:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}
}