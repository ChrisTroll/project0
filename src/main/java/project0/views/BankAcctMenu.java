package project0.views;

import java.math.BigDecimal;
import java.util.ArrayList;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class BankAcctMenu implements View{
	ArrayList<BankAcct> bankaccts;
	User user;
	int activeindex;
	char acctaction;

	public BankAcctMenu(User userin) {
		this.user = userin;
	}

	@Override
	public View process() {

		UserDao dao = new UserDao();
		bankaccts = dao.fetchBankAccts(user);
		this.activeindex = -1;
		
		if(bankaccts.size() == 0) {
			System.out.println("No bank accounts for this user.");
			System.out.println("Returning to User Menu...");
			return new UserMenu(this.user);
		}
		
		System.out.println("----- Account Management -----");
		
		//asks user to set active account index.
		chooseAcct();
		if (this.activeindex == -1) {
			return new UserMenu(this.user);
		}
		
		//asks user to choose account action
		chooseAction();
		
		switch(acctaction) {
		case 'D': return deposit();
		case 'W': return withdraw(); 
		case 'T': return transfer();
		case 'C': return close();
		case 'P': return changeDefense();
		case 'R': return new UserMenu(this.user);
		default: return new UserMenu(this.user);
		}
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
		System.out.println("Press 0 to go back.");
		
		printAcctSelect();
		
		int acctchoice = ScannerUtil.getIntIn();
		if (0 <= acctchoice && acctchoice <= bankaccts.size()) {
			this.activeindex = acctchoice -1;
		} else {
			System.out.println("Invalid account choice.");
			System.out.println("Returning to Account Management...");
		}
	}
	
	private void chooseAction() {
		System.out.println("What would you like to do?");
		System.out.println("Deposit funds: D");
		System.out.println("Withdraw funds: W");
		System.out.println("Transfer funds: T");
		System.out.println("Close Account: C");
		System.out.println("Manage Account Defense Plan: P");
		System.out.println("Return to User Menu: R");
		
		
		this.acctaction = ScannerUtil.getCharIn();
	} 
	
	private View deposit() {
		System.out.println("How much would you like to deposit?");
		BigDecimal depositamount = ScannerUtil.getBigDecimal();
		UserDao dao = new UserDao();
		dao.deposit(depositamount, this.bankaccts.get(this.activeindex));
		System.out.println("Deposit successful.");
		return new BankAcctMenu(this.user);
	}
	
	private View withdraw() {
		System.out.println("How much would you like to withdraw?");
		BigDecimal withdrawamount = ScannerUtil.getBigDecimal();
		UserDao dao = new UserDao();
		if(dao.isValidWithdrawl(this.bankaccts.get(activeindex), withdrawamount)) {
			dao.withdraw(withdrawamount, this.bankaccts.get(this.activeindex));
			System.out.println("Withdrawl successful.");
			return new BankAcctMenu(this.user);
		} else {
			System.out.println("Invalid withdrawl amount.");
			System.out.println("Returning to User Menu...");
			return new BankAcctMenu(this.user);
		}
	}
	
	private View transfer() {
		System.out.println("How much would you like to deposit?");
		BigDecimal transferamount = ScannerUtil.getBigDecimal();
		System.out.println("What is the account number you would like to transfer to?");
		int transfertarget = ScannerUtil.getIntIn();
		
		UserDao dao = new UserDao();
		if(dao.acctNumExists(transfertarget)) {
			dao.transfer(transferamount, this.bankaccts.get(this.activeindex), transfertarget);
			System.out.println("Transfer successful.");
		} else {
			System.out.println("Sorry, that account isn't in our system. ");
			System.out.println("Returning to Account Management...");
		}
		return new BankAcctMenu(this.user);
	}
	
	private View close() {
		System.out.println("Are you sure you would like to close the account?");
		char choice = ScannerUtil.getCharIn();
		while (choice != 'Y' && choice != 'N') {
			choice = ScannerUtil.getCharIn();
		}
		if (choice == 'Y') {
			UserDao dao = new UserDao();
			dao.closeBankAcct(this.bankaccts.get(this.activeindex));
			System.out.println("Account closed sucessfully.");
		}
		return new BankAcctMenu(this.user);
	}
	
	private View changeDefense() {
		BankAcct activeacct = this.bankaccts.get(activeindex);
		activeacct.askDefense();
		UserDao dao = new UserDao();
		dao.setDefense(activeacct.getDefenseID(), activeacct);
		System.out.println("Defense plan changed sucessfully.");
		return new BankAcctMenu(this.user);
	}
}
