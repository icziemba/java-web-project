package ziemba.ian.test2.authentication;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * The AuthenticationSystem class is intended to provide authentication services for another
 * system. The AuthenticationSystem works be using the AuthenticationDBConnection class to connect
 * to the underlying database.
 * 
 * @author Ian Ziemba
 * @see AuthenticationDBConnection
 * @see AuthenticationUser
 */
public class AuthenticationSystem {
	
	/**
	 * Authenticates user name and password with information in the Authentication database.
	 * @param userName User name to be checked with database.
	 * @param password Password to be checked with database.
	 * @return Status of whether the authentication was successfully or not.
	 * @throws RuntimeException Unable to authenticate the user name and password for some reason.
	 * @see AuthenticationDBConnection
	 */
	public static boolean authenticateUser(String userName, String password) throws RuntimeException {
		
		// Get the user's information from the system
		AuthenticationUser user;
		try {
			user = AuthenticationDBConnection.getAuthenticationUser(userName);
		} catch (NamingException | SQLException e) {
			throw new RuntimeException(e);
		}
		
		// Verify user name and password matches entry in database
		if(user != null) {
			if(user.getUserName().contentEquals(userName) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Register a new user with the Authentication database. The attributes of the
	 * AuthenticationUser class represent the User table which the information will be entered
	 * into.
	 * @param user Information to be entered into the database.
	 * @throws RuntimeException Unable to register the user from some reason.
	 */
	public static void registerUser(AuthenticationUser user) throws RuntimeException {
		try {
			AuthenticationDBConnection.insertAutenticationUser(user);
		} catch (NamingException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
