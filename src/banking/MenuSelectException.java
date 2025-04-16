package banking;

public class MenuSelectException extends Exception {

	public MenuSelectException() {

		super("메뉴 입력 예외 발생함");
		System.out.println("메뉴는 1~7사이 정수를 입력하시오.");

	}
}