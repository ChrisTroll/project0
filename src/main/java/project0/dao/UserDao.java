package project0.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.util.ConnectionUtil;

//User Data Access Object 

public class UserDao {

	public UserDao() {

	}
	
	//adds record to database of a new user
	public void putUser(String unamein, String pwordin) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO users (username, passphrase) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, unamein);
			statement.setString(2, pwordin);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean userExists(String unamein) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, unamein);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	//returns user object for a username and password combination
	public User getUser(String unamein, String pwordin) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ? AND passphrase = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, unamein);
			statement.setString(2, pwordin);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = extractUser(resultSet);
			
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//returns a single user from a result set of (possibly) multiple users
	private User extractUser(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("userid");
		String username = resultSet.getString("username");
		String password = resultSet.getString("passphrase");
		
		User user = new User(id, username, password);
		
		return user;
	}
	
	//returns true if an account number already exists in the database
	public boolean acctNumExists(int numin) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_accounts WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, numin);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			if (resultSet.getRow() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}


	//adds a new bank account record
	public void putBankAcct(BankAcct acctin) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bank_accounts (externalID, userID, jointID, currencyID, amount, defenseID) "
											+ "values (?, ?, ?, ? , ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, acctin.getExternalID());
			statement.setInt(2, acctin.getUserID());
			statement.setInt(3, acctin.getJointID());
			statement.setInt(4, acctin.getCurrencyID());
			statement.setBigDecimal(5, acctin.getAmount());
			statement.setInt(6, acctin.getDefenseID());

			statement.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BankAcct> fetchBankAccts(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_accounts WHERE userid = ? OR jointid IN (SELECT jointid FROM joint_map WHERE userid = ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, user.getUid());
			statement.setInt(2, user.getUid());

			ResultSet resultset = statement.executeQuery();
			
			return settoList(resultset);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static ArrayList<BankAcct> settoList(ResultSet resultset){
		try {
			ArrayList<BankAcct> accounts = new ArrayList<BankAcct>();
			while(resultset.next()) {
				accounts.add(extractBankAcct(resultset));
			}
			
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BankAcct extractBankAcct(ResultSet resultSet) throws SQLException {	
		int id = resultSet.getInt("userid");
		int externalid = resultSet.getInt("externalid");
		int jointid = resultSet.getInt("jointid");
		int currencyid = resultSet.getInt("currencyid");
		BigDecimal amount = resultSet.getBigDecimal("amount");
		int defenseid = resultSet.getInt("defenseid");
		
		BankAcct account = new BankAcct(id,externalid,jointid, currencyid, amount, defenseid);
		
		return account;
	}
	
	public void deposit(BigDecimal amountin, BankAcct account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_accounts SET amount = amount + CAST(? AS MONEY) WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			String stramount = amountin.toString();
			
			statement.setString(1, stramount);
			statement.setInt(2, account.getExternalID());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void withdraw(BigDecimal amountin, BankAcct account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_accounts SET amount = amount - CAST(? AS MONEY) WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			String stramount = amountin.toString();
			
			statement.setString(1, stramount);
			statement.setInt(2, account.getExternalID());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void transfer(BigDecimal transferamount, BankAcct account, int transfertarget) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_accounts SET amount = amount - CAST(? AS MONEY) WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);


			String stramount = transferamount.toString();

			statement.setString(1, stramount);
			statement.setInt(2, account.getExternalID());

			statement.executeUpdate();
			
			sql = "UPDATE bank_accounts SET amount = amount + CAST(? AS MONEY) WHERE externalid = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, stramount);
			statement.setInt(2, transfertarget);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void closeBankAcct(BankAcct account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM bank_accounts WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, account.getExternalID());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDefense(int defensein, BankAcct account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE bank_accounts SET defenseID = ? WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, defensein);
			statement.setInt(2, account.getExternalID());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int createNewJointID(){
		String sql = "SELECT MAX(jointid) FROM joint_map";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultset = statement.executeQuery();
			

			resultset.next();
			
			/*
			 * A QUICK WORD ABOUT THIS TRY/CATCH BLOCK
			 * the query above CAN return NULL if there are no joint accounts present
			 * so, you can cast the resulting NULL into an integer
			 * and force a NullPointerException
			 * 
			 * And no, you cannot add a placeholder record of 0, 0 in that table because it violated foreign key relationships
			 * 
			 * Look, i know its ugly but it works
			 */
			try {
				int maxint = Integer.parseInt(resultset.getObject(1).toString());
				return maxint + 1;
			} catch (NullPointerException e) {
				return 1;
			}
			
			// I wish I could use this
//			return resultset.getInt(1);
			
			
		} catch (SQLException e){
			e.printStackTrace();
			return (Integer) null;
		}
	}
	
	public void putJointAcct(ArrayList<User> acctusers, BankAcct acctin) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			for(User user: acctusers) {
				String sql = "INSERT INTO joint_map (userid, jointid) "
						+ "values (?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, user.getUid());
				statement.setInt(2, acctin.getJointID());
				
				statement.executeUpdate(); 
				
			}
			
			String sql = "INSERT INTO bank_accounts (externalID, userID, jointID, currencyID, amount, defenseID) "
											+ "values (?, ?, ?, ? , ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, acctin.getExternalID());
			statement.setInt(2, acctin.getUserID());
			statement.setInt(3, acctin.getJointID());
			statement.setInt(4, acctin.getCurrencyID());
			statement.setBigDecimal(5, acctin.getAmount());
			statement.setInt(6, acctin.getDefenseID());

			statement.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValidWithdrawl(BankAcct account, BigDecimal amount) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT amount FROM bank_accounts WHERE externalid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, account.getExternalID());
			
			ResultSet resultset = statement.executeQuery();
			
			resultset.next();
			
			if (resultset.getBigDecimal(1).compareTo(amount) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

