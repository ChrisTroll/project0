package Project0.test;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;

import project0.acctobjects.User;
import project0.dao.UserDao;

public class TestDaoMethods {
	User testuser = Mockito.mock(User.class);
	UserDao dao = new UserDao();
	
	@Test
	public void testAcctNumExists() {
		assertTrue(dao.acctNumExists(630480970));
	}
	
	public void testUserExists() {
		//this is a test user
		assertTrue(dao.userExists("banana"));
	}
	
	public void testGetUser() {
		
	}
}
