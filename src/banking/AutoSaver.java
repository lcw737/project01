package banking;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class AutoSaver extends Thread {
	private final HashSet<Account> accounts;

	public AutoSaver(HashSet<Account> accounts) {
		this.accounts = accounts;
		setDaemon(true); // 데몬쓰레드 설정 (프로그램 종료시 자동 종료)
	}

	@Override
	public void run() {
		while (true) {
			try (BufferedWriter out = new BufferedWriter(new FileWriter("AutoSaveAccount.txt"))) {
				for (Account account : accounts) {
					out.write(account.toString());
					out.newLine();
				}
				System.out.println("(자동저장) 계좌정보가 저장되었습니다.");
				sleep(5000);
			}  catch (IOException e) {
				System.out.println("IO 오류 발생");
			} catch (InterruptedException e) {
				System.out.println("자동저장이 중단되었습니다.");
				return; // 쓰레드 종료
			}
		}
	}
}
