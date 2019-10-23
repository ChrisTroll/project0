package Project0.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;

public class TestBankAcctMethods {
	
	UserDao dao = Mockito.mock(UserDao.class);
	BankAcct account = new BankAcct(630480970, 1, 0, 1, BigDecimal.valueOf(0), 4);
	User user = new User(3, "banana", "batman");

	@Test
	public void testGenExtID(){
		Mockito.when(dao.acctNumExists(account.getExternalID())).thenReturn(false);
		
		account.genExtID();
		
		assertEquals("Generating a new account number should return a number between 1,000,000 and 999,999,999",
			(1000000 < account.getExternalID() && account.getExternalID() < 999999999), true);
	}

}
