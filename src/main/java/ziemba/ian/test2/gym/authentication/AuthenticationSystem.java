package ziemba.ian.test2.gym.authentication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;
import ziemba.ian.test2.gym.authentication.User;

/**
 * The AuthenticationSystem class is intended to provide authentication services for another
 * a gym system. The AuthenticationSystem uses object relational mapping (ORM) to map User.class
 * to the User table in a GymSystem database.
 * 
 * @author Ian Ziemba
 * @see AuthenticationDBConnection
 * @see User
 */
public class AuthenticationSystem {
	
	/**
	 * Given a user name and password, check if the pair match some entry in the database.
	 * @param userName User name to be checked with database.
	 * @param password Password to be checked with database.
	 * @return Status of whether the authentication was successfully or not.
	 * @see User
	 */
	public static boolean authenticateUser(String userName, String password) {
		User user = getUser(userName);
		return user.getUserName().contentEquals(userName) && user.getPassword().equals(password);
	}
	
	/**
	 * Register a new user. User name of the User object must be unique.
	 * @param user Information to be entered into the database.
	 * @throws RuntimeException Unable to register the user from some reason.
	 */
	public static void registerUser(User user) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Remove a user from the the database.
	 * @param user User object to be removed.
	 */
	public static void removeUser(User user) {
		
		// JPA requires a find operation before a remove operation. So, use the primary key of the
		// user to issue the find.
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		User removedUser = em.find(User.class, user.getIdUser());
		em.getTransaction().begin();
		em.remove(removedUser);
		em.getTransaction().commit();
		em.close();
	}
	
	
	/**
	 * Check is a user name already exists.
	 * @param userName String to be checked.
	 * @return True if userName exists. Else false.
	 */
	public static boolean userNameExists(String userName) {
		return getUser(userName) != null;
	}
	
	/**
	 * Get a User object based on a specific user name. The row must exist in the database.
	 * @param userName User name to be checked.
	 * @return On success, valid user object. Else null.
	 */
	public static User getUser(String userName) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		
		// "find the user by username" is a NamedNativeQuery defined in User.java
		Query query = em.createNamedQuery("find user by user name");
		query.setParameter(1, userName);
		
		try {
			return (User) query.getSingleResult();
		} catch(javax.persistence.NoResultException e) {
			return null;
		}
	}
}
