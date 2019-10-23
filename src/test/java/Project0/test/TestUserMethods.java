package Project0.test;

import static org.junit.Assert.*;

import org.junit.Test;

import project0.acctobjects.User;

public class TestUserMethods {
	User user = new User(3, "banana", "batman");
	
	@Test
	public void testGetUID() {
		int userID = user.getUid();
		assertEquals("getUID should return the userID of a given user", userID, 3);
	}
	
	@Test
	public void testGetUserName() {
		String username = user.getUsername();
		assertEquals("getUID should return the userID of a given user", username, "banana");
	}

	public void testGetPassword() {
		String password = user.getUsername();
		assertEquals("getUID should return the userID of a given user", password, "batman");
	}
}
