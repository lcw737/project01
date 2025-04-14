package banking;

public class Account {

	String bname;
	String bnum;
	int balance;
	
	public Account(String bname, String bnum, int balance) {
		this.bname = bname;
		this.bnum = bnum;
		this.balance = balance;
	}
	
	void showAccInfo1() {
		System.out.println("계좌번호:"+ bnum);
		System.out.println("고객이름:"+bname);
		System.out.println("잔고:"+balance);
	}
}
