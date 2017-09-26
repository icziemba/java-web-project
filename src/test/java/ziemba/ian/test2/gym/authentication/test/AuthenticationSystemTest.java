package ziemba.ian.test2.gym.authentication.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ziemba.ian.test2.gym.authentication.AuthenticationSystem;
import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.authentication.UserLevel;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

public class AuthenticationSystemTest {

	private EntityManagerFactoryListener emfl;
	private User user;
	
	@Before
	public void setUp() throws Exception {
		emfl = new EntityManagerFactoryListener();
		emfl.contextInitialized(null);
		user  = new User("icziemba", "password", UserLevel.ADMIN);
	}
	
	@After
	public void tearDown() throws Exception {
		emfl.contextDestroyed(null);
		emfl = null;
		user = null;
	}
	
	@Test
	public void registerRemoveUser() {
		AuthenticationSystem.registerUser(user);
		assertEquals("User was not successfully registered in the database",user, AuthenticationSystem.getUser(user.getUserName()));
		AuthenticationSystem.removeUser(user);
		assertEquals("User was not successfully removed from the database",false, AuthenticationSystem.userNameExists(user.getUserName()));
	}

	@Test
	public void authenticateUserSuccess() {
		AuthenticationSystem.registerUser(user);
		assertEquals("User was not successfully authenticated", true, AuthenticationSystem.authenticateUser(user.getUserName(), user.getPassword()));
		AuthenticationSystem.removeUser(user);
	}
	
	@Test
	public void authenticateUserFailure() {
		AuthenticationSystem.registerUser(user);
		assertEquals("User was not successfully authenticated", false, AuthenticationSystem.authenticateUser(user.getUserName(), "foobar"));
		AuthenticationSystem.removeUser(user);
	}
}
