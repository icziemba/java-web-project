package ziemba.ian.test2.gym.authentication.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.authentication.UserLevel;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

public class AuthenticationSystemTest {

	private EntityManagerFactoryListener emfl;
	
	@Before
	public void setUp() throws Exception {
		emfl = new EntityManagerFactoryListener();
		emfl.contextInitialized(null);
	}
	
	@After
	public void tearDown() throws Exception {
		emfl.contextDestroyed(null);
		emfl = null;
	}
	
	@Test
	public void registerRemoveUser() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		User.registerUser(user);
		assertEquals("User was not successfully registered in the database",user, User.getUser(user.getUserName()));
		User.removeUser(user);
		assertEquals("User was not successfully removed from the database",false, User.userNameExists(user.getUserName()));
	}

	@Test
	public void authenticateUserSuccess() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		User.registerUser(user);
		assertEquals("User was not successfully authenticated", true, User.authenticateUser(user.getUserName(), user.getPassword()));
		User.removeUser(user);
	}
	
	@Test
	public void authenticateUserFailure() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		User.registerUser(user);
		assertEquals("User was not successfully authenticated", false, User.authenticateUser(user.getUserName(), "foobar"));
		User.removeUser(user);
	}
}
