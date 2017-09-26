package ziemba.ian.test2.gym.authentication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;
import ziemba.ian.test2.gym.authentication.User;

/**
 * The AuthenticationSystem class is intended to provide authentication services for another
 * system. The AuthenticationSystem works be using the AuthenticationDBConnection class to connect
 * to the underlying database.
 * 
 * @author Ian Ziemba
 * @see AuthenticationDBConnection
 * @see User
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
	public static boolean authenticateUser(String userName, String password) {
		User user = getUser(userName);
		return user.getUserName().contentEquals(userName) && user.getPassword().equals(password);
	}
	
	/**
	 * Register a new user with the Authentication database. The attributes of the
	 * AuthenticationUser class represent the User table which the information will be entered
	 * into.
	 * @param user Information to be entered into the database.
	 * @throws RuntimeException Unable to register the user from some reason.
	 */
	public static void registerUser(User user) throws RuntimeException {
		
		// Use the java persistence API (JPA) to create a object-relation mapping (ORM));
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		em.getTransaction().begin();
		
		// Commit the user to the Authentication database
		em.persist(user);
		em.getTransaction().commit();
		
		em.close();
	}
	
	private static User getUser(String userName) {
		// Use the java persistence API (JPA) to create a object-relation mapping (ORM)
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		
		// Find the user by user name
		Query query = em.createNamedQuery("find user by username");
		query.setParameter(1, userName);
		
		Object user = query.getSingleResult();
		em.close();
		
		return (User) user;
	}
}
