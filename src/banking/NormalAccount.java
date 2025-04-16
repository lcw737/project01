package banking;

public class NormalAccount extends Account {
	int interest;

	public NormalAccount(String bname, String bnum, int balance, int interest) {
		super(bname, bnum, balance);
		this.interest = interest;
	}

	@Override
	public String getAccountType() {
	    return "보통계좌";
	}

	public int getInterest() {
	    return interest;
	}
}