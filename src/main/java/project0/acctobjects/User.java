package project0.acctobjects;

public class User {

	private int uid;
	private String username;
	private String password;
	
	public User(int idin, String userin, String passin) {
		// TODO Auto-generated constructor stub
		super();
		this.uid = idin;
		this.username = userin;
		this.password = passin;
	}
	
	@Override
	public String toString(){
		return "UserID: " + this.uid + ", Username: "  + this.username + ", Password: " + this.password;
	}
	
	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



}
