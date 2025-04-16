package banking;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine, Serializable {

	public static Scanner scan = new Scanner(System.in);

	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.계좌삭제");
		System.out.println("6.저장옵션");
		System.out.println("7.프로그램종료");
	}

	public static void main(String[] args) {

		AccountManager handler = new AccountManager();

		handler.LoadObject();

		while (true) {
			try {
				showMenu();
				System.out.print("메뉴입력:");
				int choice = scan.nextInt();
				scan.nextLine();
				switch (choice) {
				case ICustomDefine.MAKE:
					handler.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					handler.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					handler.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					handler.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					handler.deleteAccount();
					break;
				case ICustomDefine.SAVE:
				    handler.manageAutoSave();
				    break;
				case ICustomDefine.EXIT:
					handler.OutputObject();
					System.out.println("프로그램종료");
					return;
				default:
					System.out.println("숫자로 입력해주세요");
					break;
				}// switch
			} // try
			catch (InputMismatchException e) {
				System.out.println("숫자만 입력할 수 있습니다.");
				scan.nextLine();
			} // catch
		} // while
	}// main
}// class
