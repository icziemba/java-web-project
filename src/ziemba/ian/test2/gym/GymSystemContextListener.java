package ziemba.ian.test2.gym;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GymSystemContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/* Create a new gym object and register it as a attribute for Servlet Context */
		GymSystem gym = new GymSystem();
		arg0.getServletContext().setAttribute("gym", gym);

	}

}
