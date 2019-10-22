package project0.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class ScannerUtil {
	static Scanner sc = new Scanner(System.in);
	
	public static String getStringIn() {
		String input = "";
		while(input.isEmpty()) {
			input = sc.nextLine();
		}
		return input;
	}
	
	public static char getCharIn() {
		Character input = null;
		while(input == null) {
			input = sc.next().charAt(0);
		}
		return input;

	}
	
	public static int getIntIn() {
		Integer input = null;
		while(input == null) {
			input = sc.nextInt();
		}
		return input;
	}
	
	public static BigDecimal getBigDecimal() {
		BigDecimal input = null;
		while(input == null) {
			input = sc.nextBigDecimal();
		}
		return input;
	}
	
}
