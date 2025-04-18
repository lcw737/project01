package banking;

import java.io.Serializable;

public abstract class Account implements Serializable {

	
	String bname;
	String bnum;
	int balance;

	public Account(String bname, String bnum, int balance) {
		this.bname = bname;
		this.bnum = bnum;
		this.balance = balance;
	}

	public abstract void deposit(int amount);
	
	void showAccInfo1() {
		System.out.println("계좌번호:" + bnum);
		System.out.println("고객이름:" + bname);
		System.out.println("잔고:" + balance);
		System.out.println("------------------");
		
	}
	@Override
	public String toString() {
		return "Account [계좌번호=" + bname + ", 고객이름=" + bnum + ", 잔고=" + balance + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Account) {
			Account other = (Account) obj;
			return this.bnum.equals(other.bnum);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return bnum.hashCode();
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountType() {
	    return "기본계좌";
	}

	public int getInterest() {
		return 0;
	}

}
