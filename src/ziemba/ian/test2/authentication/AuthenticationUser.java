package ziemba.ian.test2.authentication;

/**
 * Class representation of the User table in the Authentication database. Each attribute of the
 * class should represent a row in the User table.
 * 
 * @author Ian Ziemba
 * @see AuthenticationSystem
 * @see AuthenticationDBConnection
 */
public class AuthenticationUser {
	
	private int id = 0;
	private String userName;
	private String password;
	
	public AuthenticationUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	protected AuthenticationUser(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
