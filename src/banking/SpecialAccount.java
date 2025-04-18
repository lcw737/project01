package banking;

public class SpecialAccount extends NormalAccount {
    
    private int depositCount;

    public SpecialAccount(String bname, String bnum, int balance, int interest) {
        super(bname, bnum, balance, interest);
        this.depositCount = 0;
    }

    @Override
    public void deposit(int amount) {
        depositCount++;

        int interestAmount = amount * getInterest() / 100;
        int bonus;

        if (depositCount % 2 == 0) {
            bonus = 500;
        } else {
            bonus = 0;
        }

        setBalance(getBalance() + amount + interestAmount + bonus);
    }

    @Override
    public void showAccInfo1() {
        super.showAccInfo1();
        System.out.println("입금 횟수: " + depositCount + "회");
    }

    @Override
    public String getAccountType() {
        return "특판계좌";
    }
}
