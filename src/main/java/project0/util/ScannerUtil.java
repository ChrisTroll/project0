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
		int num = -1;
		do {
			while(!sc.hasNextInt()) {
				sc.next();
			}
			num = sc.nextInt();
		} while (num <= -1);
		return num;
	}
	
	public static BigDecimal getBigDecimal() {
		BigDecimal num = null;
		do {
			while(!sc.hasNextBigDecimal()) {
				sc.next();
			}
			num = sc.nextBigDecimal();
		} while (num == null);
		return num;
	}
	
}
