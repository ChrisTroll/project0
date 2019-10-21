package project0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project0.acctobjects.BankAcct;
import project0.acctobjects.User;
import project0.util.ConnectionUtil;

//User Data Access Object 

public class UserDao {

	public UserDao() {
		// TODO Auto-generated constructor stub
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
}

