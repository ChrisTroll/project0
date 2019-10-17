package project0.views;

import project0.util.ScannerUtil;

public class Landing implements View{
	
	public void printMenu() {
		System.out.println("$$$$$ Main Menu $$$$$");
		System.out.println("Login: L");
		System.out.println("Create user account: N");
		System.out.println("Quit: Q");
	}

	public View process() {
		printMenu();
		char charin = ScannerUtil.getCharIn();
		switch(charin) {
			case 'L': return new LoginMenu();
			case 'N': return new UserMenu();
			default: return null;
		}
			
	}
	
	public void greet(){
		System.out.println("Welcome to HAMMERVAULT, we only store HAMMMERS!");

		System.out.println(" /$$   /$$");
		System.out.println("| $$  | $$");
		System.out.println("| $$  | $$  /$$$$$$  /$$$$$$/$$$$  /$$$$$$/$$$$   /$$$$$$   /$$$$$$ ");
		System.out.println("| $$$$$$$$ |____  $$| $$_  $$_  $$| $$_  $$_  $$ /$$__  $$ /$$__  $$");
		System.out.println("| $$__  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$ \\ $$ \\ $$| $$$$$$$$| $$  \\__/");
		System.out.println("| $$  | $$ /$$__  $$| $$ | $$ | $$| $$ | $$ | $$| $$_____/| $$  ");
		System.out.println("| $$  | $$|  $$$$$$$| $$ | $$ | $$| $$ | $$ | $$|  $$$$$$$| $$");
		System.out.println("|__/  |__/ \\_______/|__/ |__/ |__/|__/ |__/ |__/ \\_______/|__/");
		System.out.println("");
		System.out.println(" /$$    /$$                    /$$   /$$  ");
		System.out.println("| $$   | $$                   | $$  | $$");
		System.out.println("| $$   | $$ /$$$$$$  /$$   /$$| $$ /$$$$$$");
		System.out.println("|  $$ / $$/|____  $$| $$  | $$| $$|_  $$_/  ");
		System.out.println(" \\  $$ $$/  /$$$$$$$| $$  | $$| $$  | $$");
		System.out.println("  \\  $$$/  /$$__  $$| $$  | $$| $$  | $$ /$$");
		System.out.println("   \\  $/  |  $$$$$$$|  $$$$$$/| $$  |  $$$$/");
		System.out.println("    \\_/    \\_______/ \\______/ |__/   \\___/");
	}
	
	public void goodbye() {
		System.out.println("Thanks for using HAMMERVAULT, we only store HAMMMERS!");

		System.out.println(" /$$   /$$");
		System.out.println("| $$  | $$");
		System.out.println("| $$  | $$  /$$$$$$  /$$$$$$/$$$$  /$$$$$$/$$$$   /$$$$$$   /$$$$$$ ");
		System.out.println("| $$$$$$$$ |____  $$| $$_  $$_  $$| $$_  $$_  $$ /$$__  $$ /$$__  $$");
		System.out.println("| $$__  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$ \\ $$ \\ $$| $$$$$$$$| $$  \\__/");
		System.out.println("| $$  | $$ /$$__  $$| $$ | $$ | $$| $$ | $$ | $$| $$_____/| $$  ");
		System.out.println("| $$  | $$|  $$$$$$$| $$ | $$ | $$| $$ | $$ | $$|  $$$$$$$| $$");
		System.out.println("|__/  |__/ \\_______/|__/ |__/ |__/|__/ |__/ |__/ \\_______/|__/");
		System.out.println("");
		System.out.println(" /$$    /$$                    /$$   /$$  ");
		System.out.println("| $$   | $$                   | $$  | $$");
		System.out.println("| $$   | $$ /$$$$$$  /$$   /$$| $$ /$$$$$$");
		System.out.println("|  $$ / $$/|____  $$| $$  | $$| $$|_  $$_/  ");
		System.out.println(" \\  $$ $$/  /$$$$$$$| $$  | $$| $$  | $$");
		System.out.println("  \\  $$$/  /$$__  $$| $$  | $$| $$  | $$ /$$");
		System.out.println("   \\  $/  |  $$$$$$$|  $$$$$$/| $$  |  $$$$/");
		System.out.println("    \\_/    \\_______/ \\______/ |__/   \\___/");
	}
	
	public void mission() {
		System.out.println("HAMMERVAULT is committed to protecting your hammers using a new technology called:");
		System.out.println("HAMMER SPACE");
	}

}
