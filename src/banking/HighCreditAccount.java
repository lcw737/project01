package banking;

public class HighCreditAccount extends Account {
    int interest;   // 기본이자율
    char grade;     // 신용등급 A, B, C

    public HighCreditAccount(String bname, String bnum, int balance, int interest, char grade) {
        super(bname, bnum, balance);
        this.interest = interest;
        this.grade = grade;
    }

    public int ExtraInterest() {
        switch (grade) {
            case 'A': return ICustomDefine.RATE_A;
            case 'B': return ICustomDefine.RATE_B;
            case 'C': return ICustomDefine.RATE_C;
            default: return 0;
        }
    }
}