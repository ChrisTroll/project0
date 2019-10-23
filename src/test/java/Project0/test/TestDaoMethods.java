package Project0.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.dao.UserDao;

public class TestDaoMethods {
	User testuser = Mockito.mock(User.class);
	UserDao dao = new UserDao();
	
	@Test
	public void testAcctNumExists() {
		//this is a test account
		assertTrue(dao.acctNumExists(630480970));
	}
	
	@Test
	public void testUserExists() {
		//this is a test user
		assertTrue(dao.userExists("banana"));
	}
	
	@Test
	public void testCreateNewJointID() {
		//cannot store more than 1000 in BigDecimal
		int result = dao.createNewJointID();
		assertTrue(result >= 0);
	}
	
}
