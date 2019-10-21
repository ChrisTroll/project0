package project0.views;

import project0.acctobjects.User;
import project0.util.ScannerUtil;

public class UserMenu implements View{
	User user;
	
	UserMenu(){
		super();
	}
	
	UserMenu(User userin) {
		super();
		this.user = userin;
	}
	
	public void printMenu() {
		System.out.println("Welcome " + this.user.getUsername() + "!");
		System.out.println("What would you like to do today?");
		System.out.println("Add funds: A");
		System.out.println("Withdraw funds: W");
		System.out.println("Transfer funds: T");
		System.out.println("Open Account: O");
		System.out.println("Create Join Account: J");
		System.out.println("Close Account: C");
		System.out.println("Manage Account Defense Plan: D");
		System.out.println("Return to Main Menu: R");
	}
	
	@Override
	public View process() {
		// TODO Auto-generated method stub
		printMenu();
		char charin = ScannerUtil.getCharIn();
		switch(charin) {
			case 'A': return null;
			case 'W': return null;
			case 'T': return null;
			case 'O': return null;
			case 'J': return null;
			case 'C': return null;
			case 'D': return null;
			case 'R': return new Landing();
			default: return null;
		}
	}

}
