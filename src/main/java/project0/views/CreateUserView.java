package project0.views;

import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class CreateUserView implements View{
	private String username = "";
	private String password = "";

	//asks user for their username
	public void setUsername() {
		while (this.username.isEmpty()){
			System.out.println("Choose a username: ");
			username = ScannerUtil.getStringIn();
		}
	}
	
	//asks user for their password
	public void setPassword() {
		while(this.password.isEmpty()) {
			System.out.println("Enter a secure password: ");
			password = ScannerUtil.getStringIn();
		}
	}
	
	//compare username and password to users in database, reroute as necessary
	@Override
	public View process(){
		System.out.println("----- User Creation -----");
		
		setUsername();
		setPassword();
		
		UserDao dao = new UserDao();
		boolean userexists = dao.userExists(this.username);
		if (!userexists) {
			dao.putUser(this.username, this.password);
			return new Landing();
		} else {
			System.out.println("Sorry, that username is taken.");
			System.out.println("Returning to landing...");
			return new Landing();
		}
	}
	
}
