package ziemba.ian.test2.gym.users;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ziemba.ian.test2.gym.authentication.User;
import ziemba.ian.test2.gym.listeners.EntityManagerFactoryListener;

public class ManagerOperations {

	public static void registerManager(Manager manager) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		em.getTransaction().begin();
		em.persist(manager);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void removeManager(Manager manager) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		Manager removedUser = em.find(Manager.class, manager.getIdManager());
		em.getTransaction().begin();
		em.remove(removedUser);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Manager getManager(User user) {
		EntityManager em = EntityManagerFactoryListener.createEntityManager();
		Query query = em.createNamedQuery("find manager by user");
		query.setParameter(1, user.getIdUser());
		
		try {
			return (Manager) query.getSingleResult();
		} catch(javax.persistence.NoResultException e) {
			return null;
		}
	}
}
