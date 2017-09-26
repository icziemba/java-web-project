package ziemba.ian.test2.gym.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ziemba.ian.test2.gym.authentication.AuthenticationSystem;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "User login for gym system.", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false) != null) {
			gotoMain(response);
		}
		else {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		boolean authenticated = AuthenticationSystem.authenticateUser(userName, password);
		if(authenticated) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", userName);
			gotoMain(response);
		}
		else {
			out.println("Unsuccessfully logged in :(");
		}
	}
	
	private void gotoMain(HttpServletResponse response) {
		try {
			response.sendRedirect("main");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
