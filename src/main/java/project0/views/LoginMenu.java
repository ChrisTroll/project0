package project0.views;

import project0.util.ScannerUtil;
import project0.views.Landing;

public class LoginMenu implements View{
	private String username = "";
	private String password = "";

	//asks user for their username
	public void userNameIn() {
		while (this.username.isEmpty()){
			System.out.println("Please enter your username: ");
			username = ScannerUtil.getStringIn();
		}
	}
	
	//asks user for their password
	public void passwordIn() {
		while(this.password.isEmpty()) {
			System.out.println("Please enter your password: ");
			password = ScannerUtil.getStringIn();
		}
	}
	
	//compare username and password to users in database, reroute as necessary
	@Override
	public View process() {
		System.out.println("Login...");
		userNameIn();
		passwordIn();
		
		//if creds are valid
			//move to UserMenu()
		//else 
			//return to Landing()
		System.out.println("Invalid username/password combination.");
		return null;
	}
	
	

}
