package project0.acctobjects;

import java.math.BigDecimal;
import java.util.Random;

import project0.dao.UserDao;
import project0.util.ScannerUtil;

public class BankAcct {
	private int externalID;
	private int userID;
	private int jointID;
	private int currencyID;
	private BigDecimal amount;
	private int defenseID;

	public BankAcct(int idin, int externalidin, int jointidin, int currencyidin, BigDecimal amountin, int defenseidin) {
		this.userID = idin;
		this.externalID = externalidin;
		this.jointID = jointidin;
		this.currencyID = currencyidin;
		this.amount = amountin;
		this.defenseID = defenseidin;
	}
	//returns an integer that has not been used as an external accountid
	public void genExtID() {
		Random rangen = new Random();
		//generates a random integer between 1,000,000 and 999,999,999
		int genint;
		UserDao dao = new UserDao();
		
		do {
			genint = rangen.nextInt(998999999) + 1000000;
		}while (dao.acctNumExists(genint));
		
		this.externalID = genint;
		
	}

	
	public void askDefense() {
		System.out.println("What protection would you like?");
		System.out.println("Wizards: 1");
		System.out.println("Hydras: 2");
		System.out.println("Dragons: 3");	
		System.out.println("Pirates :4");	
		System.out.println("Ninjas : 5");
		System.out.println("Vikings : 6");
		
		Integer intin = null;
		while (intin == null || intin < 1 || intin > 6) {
			intin = ScannerUtil.getIntIn();
		}
		defenseID = intin;
	}
	
	public String toString(){
		
		return "Account: [Account Number = " + this.externalID + 
					" Currency = " + this.currencytoString() +
					" Amount = " + this.amount +
					" Defense = " + this.defensetoString() + "]";
	}
	
	private String currencytoString() {
		switch(this.currencyID) {
		case 1: return "USD";
		case 2: return "EUR";
		case 3: return "BPH";
		case 4: return "WRH";
		case 5: return "SLH";
		default: return null;
		}
	}
	
	private String defensetoString() {
		switch(this.defenseID) {
		case 1: return "Wizards";
		case 2: return "Hydras";
		case 3: return "Dragons";
		case 4: return "Pirates";
		case 5: return "Ninjas";
		case 6: return "Vikings";
		default: return null;
		}
	}
	
	public void genJointID() {
		UserDao dao = new UserDao();
		this.jointID = dao.createNewJointID();
	}

	
	public int getExternalID() {
		return externalID;
	}

	public void setExternalID(int externalID) {
		this.externalID = externalID;
	}
	
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getJointID() {
		return jointID;
	}

	public void setJointID(int jointID) {
		this.jointID = jointID;
	}

	public int getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getDefenseID() {
		return defenseID;
	}

	public void setDefenseID(int defenseID) {
		this.defenseID = defenseID;
	}


	public BankAcct() {
	}

}
