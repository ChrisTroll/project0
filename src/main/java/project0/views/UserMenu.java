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
		System.out.println("----- Welcome " + this.user.getUsername() + "! -----");
		System.out.println("What would you like to do today?");
		System.out.println("Open Account: O");
		System.out.println("Create Joint Account: J");
		System.out.println("Manage Accounts: M");

		System.out.println("Return to Main Menu: R");
	}
	
	@Override
	public View process() {
		printMenu();
		char charin = ScannerUtil.getCharIn();
		switch(charin) {

			case 'O': return new OpenAcctView(user);
			case 'J': return null;
			case 'M': return new BankAcctMenu(user);
			case 'R': return new Landing();
			default: return null;
		}
	}

}
