package project0.views;

import java.math.BigDecimal;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class OpenAcctView implements View{
	private User acctowner;
	private BankAcct bankacct;

	public OpenAcctView(User user) {
		this.acctowner = user;
	}

	@Override
	public View process() {
		System.out.println("----- Account Creation -----");
		
		//gather information from the user
		this.bankacct = new BankAcct();
		this.bankacct.setUserID(acctowner.getUid());
		this.bankacct.genExtID();
		askCurrency();	
		askDepositAmount();
		this.bankacct.askDefense();
		
		//ask if they want a joint account
		UserDao dao = new UserDao();
		dao.putBankAcct(this.bankacct);
		
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
