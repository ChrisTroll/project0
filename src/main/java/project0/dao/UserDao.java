package project0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project0.acctobjects.User;
import project0.util.ConnectionUtil;

//User Data Access Object 

public class UserDao {

	public UserDao() {
		// TODO Auto-generated constructor stub
	}
	
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

	//extractBankAcct
	
	
	private User extractUser(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("passphrase");
		
		User user = new User(id, username, password);
		
		return user;
	}
}
