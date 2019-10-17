package project0.views;

public class UserMenu implements View{
	
	
	public void printMenu() {
		System.out.println("Welcome " /*username*/ + "!");
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
		return null;
	}

}
