package banking;

import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료");
	}
	
	public static void main(String[] args) {

		AccountManager handler = new AccountManager(50);
		
		while(true) {
			showMenu();  
			int choice = scan.nextInt();
			scan.nextLine();
			switch(choice) {
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
			case ICustomDefine.EXIT:
				System.out.println("프로그램종료");
				return;
			default :
				System.out.println("숫자로 입력해주세요");
				break;
			}
		}
	}

}
