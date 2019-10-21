package project0.views;

import java.util.ArrayList;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class BankAcctMenu implements View{
	ArrayList<BankAcct> bankaccts;
	User user;
	int activeindex;

	public BankAcctMenu(User userin) {
		this.user = userin;
	}

	@Override
	public View process() {
		System.out.println("----- Account Management -----");
		UserDao dao = new UserDao();
		bankaccts = dao.fetchBankAccts(user);
		
		//asks user to set active account index.
		chooseAcct();
		
		//asks user to choose account action
		View nextview = chooseAction();
		
		return null;
	}
	
	private void printAcctSelect() {
		int count = 1;
		for(BankAcct b: bankaccts) {
			System.out.println(count + ":" + b.toString());
			count++;
		}
	}
	
	private void chooseAcct() {
		System.out.println("What account would you like to manage?");
		printAcctSelect();
		
		int acctchoice = ScannerUtil.getIntIn();
		if (0 < acctchoice && acctchoice < bankaccts.size()) {
			this.activeindex = acctchoice -1;
		}
	}
	
	private View chooseAction() {
		System.out.println("What would you like to do?");
		System.out.println("Deposit funds: D");
		System.out.println("Withdraw funds: W");
		System.out.println("Transfer funds: T");
		System.out.println("Close Account: C");
		System.out.println("Manage Account Defense Plan: P");
		
		char charin = ScannerUtil.getCharIn();
		switch(charin) {

			case 'D': return null;
			case 'W': return null;
			case 'T': return null;
			case 'C': return null;
			case 'P': return null;
			default: return null;
		}
	}
}
