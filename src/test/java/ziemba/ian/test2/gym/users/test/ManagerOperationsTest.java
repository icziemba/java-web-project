package ziemba.ian.test2.gym.users.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.authentication.UserLevel;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;
import ziemba.ian.test2.gym.users.Manager;

public class ManagerOperationsTest {

	private EntityManagerFactoryListener emfl;;
	
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
	public void createDeleteManager() {
		User user  = new User("icziemba", "password", UserLevel.ADMIN);
		User.registerUser(user);
		Manager manager = new Manager("Ian", "Ziemba", user);
		Manager.registerManager(manager);
		assertEquals("Manager not successfully entered into database", manager, Manager.getManager(user));
		Manager.removeManager(manager);
		assertEquals("Manager not successfully removed into database", null, Manager.getManager(user));
	}

}
