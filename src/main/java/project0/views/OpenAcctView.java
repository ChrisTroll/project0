package project0.views;

import java.math.BigDecimal;
import java.util.ArrayList;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class OpenAcctView implements View{
	private User acctowner;
	private BankAcct bankacct;
	private ArrayList<User> userlist;

	public OpenAcctView(User user) {
		this.acctowner = user;
	}
	
	public OpenAcctView(ArrayList<User> userlistin, User userin) {
		this.userlist = userlistin;
		this.acctowner = userin;
	}

	@Override
	public View process() {
		System.out.println("----- Account Creation -----");
		
		//gather information from the user
		this.bankacct = new BankAcct();
		if (this.userlist.size() == 0) {
			this.bankacct.setUserID(acctowner.getUid());
		} else {
			this.bankacct.genJointID();
			this.bankacct.setUserID(0);
		}
		this.bankacct.genExtID();
		askCurrency();	
		askDepositAmount();
		this.bankacct.askDefense();
		
		//ask if they want a joint account
		UserDao dao = new UserDao();
		if (this.userlist.size() == 0) {
			dao.putBankAcct(this.bankacct);
		} else {
			dao.putJointAcct(this.userlist, this.bankacct);
		}
		
		System.out.println("Account successfully added!");
		
		return new UserMenu(acctowner);
	}
	
	public void askDepositAmount() {
		//THIS OVERWRITES THE AMOUNT
		System.out.println("How much are you storing today?");
		BigDecimal amountin = null;
		while (amountin == null) {
			amountin = ScannerUtil.getBigDecimal();
		}
		this.bankacct.setAmount(amountin);
	}
	
	public void askCurrency() {
		System.out.println("What kind of currency are you storing?");
		System.out.println("USD: 1");
		System.out.println("EUR: 2");
		System.out.println("BPH (BallPeen Hammer): 3");	
		System.out.println("WRH (Warhamnner): 4");	
		System.out.println("SLH (Sledgehammer): 5");
		
		Integer intin = null;
		while (intin == null || intin < 1 || intin > 5) {
			intin = ScannerUtil.getIntIn();
		}
		int newint = intin;
		this.bankacct.setCurrencyID(newint);
	}

}
