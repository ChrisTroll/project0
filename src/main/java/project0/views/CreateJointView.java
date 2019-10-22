package project0.views;

import java.util.ArrayList;

import project0.acctobjects.User;
import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class CreateJointView implements View{
	User activeuser;
	int numusers = 0;
	ArrayList<User> acctowners = new ArrayList<User>();

	public CreateJointView(User userin) {
		this.activeuser = userin;
	}

	@Override
	public View process() {
		System.out.println("----- Joint Account Creation -----");
		askNumUsers();
		askLogins();
		if(acctowners.size() != numusers) {
			System.out.println("One of your users had invalid credentials.");
			System.out.println("Returning to User Menu...");
			return new UserMenu(this.activeuser);
		} else {
			return new OpenAcctView(this.acctowners, this.activeuser);
		}
	}
	
	private void askNumUsers() {
		System.out.println("How many users will be on this account? (Between 2 and 5)");
		while(this.numusers > 5 || this.numusers < 2) {
			this.numusers = ScannerUtil.getIntIn();
		}
	}
	
	private void askLogins() {
		UserDao dao = new UserDao();
		User usertoadd = this.activeuser;
		int userindex = 2;
		acctowners.add(usertoadd);
		
		while(userindex <= this.numusers && usertoadd != null) {
			String username = askUsername(userindex);
			String password = askPass(userindex);	
			
			usertoadd = dao.getUser(username, password);
			if(usertoadd == null) userindex = 5;
			
			acctowners.add(usertoadd);
			userindex++;
		}
	}
	
	private String askUsername(int indexin) {
		System.out.println("Username for user # " + indexin + "?");
		String userin = "";
		while (userin.equals("")) {
			userin = ScannerUtil.getStringIn();
		}
		return userin;
	}
	
	//asks user for their password
	private String askPass(int indexin) {
		System.out.println("Password for user # " + indexin + "?");
		String userin = "";
		while (userin.equals("")) {
			userin = ScannerUtil.getStringIn();
		}
		return userin;
	}

}
