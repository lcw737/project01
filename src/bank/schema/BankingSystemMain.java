package bank.schema;

import java.util.Scanner;

public class BankingSystemMain {
	
	//키보드 입력을 위한 인스턴스
	static Scanner scan = new Scanner(System.in);
	//계좌정보 저장을 위한 인스턴스배열
	static Account[] accounts = new Account[50];
	//개설된 계좌정보 카운트용 변수 
	static int accCnt = 0;
	
	public static void showMenu() {
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료");
	}
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		System.out.print("계좌번호:");
		String a = scan.nextLine();
		System.out.print("이름:");
		String n = scan.nextLine();
		System.out.print("잔고:");
		int b = scan.nextInt();
		
		//신규계좌 생성 및 추가
		Account ac = new Account(a, n, b);
		accounts[accCnt++] = ac;
		System.out.println("신규계좌 개설 완료");
	}  
	// 입    금
	public static void depositMoney() {
		
	}  
	// 출    금
	public static void withdrawMoney(){
		
	}  
	// 전체계좌정보출력
	public static void showAccInfo(){
		for(int i=0 ; i<accCnt ; i++) {
			//toString을 오버라이딩 했으므로 인스턴스를 그대로 출력
			System.out.println(accounts[i]);
		}
		System.out.println("**전체계좌정보가 출력됨**");
	}    

	
	public static void main(String[] args) {
		
		while(true) {
			//메뉴출력
			showMenu();
			System.out.print("메뉴입력:");
			int key = scan.nextInt();
			scan.nextLine();//버퍼에 남은 엔터키 제거
			switch(key) {
			//계좌개설
			case ICustomDefine.MAKE: 
				makeAccount();
				System.out.println("계좌개설");
				break;
				//입금
			case ICustomDefine.DEPOSIT: 
				depositMoney();
				System.out.println("입금");
				break;
				//출금
			case ICustomDefine.WITHDRAW: 
				withdrawMoney();
				System.out.println("출금");
				break;
				//전체계좌정보출력
			case ICustomDefine.INQUIRE: 
				showAccInfo();
				System.out.println("전체계좌정보출력");
				break;
				//프로그램종료
			case ICustomDefine.EXIT: 
				System.exit(0);
				break;
			}//swhitch
		}//while
	}//main
}//class
