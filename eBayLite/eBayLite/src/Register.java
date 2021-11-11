

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Security;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String reEmail = request.getParameter("reEmail");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		
		if (!(email.equals(reEmail) || password.equals(rePassword))) {
			if (!email.equals(reEmail)) {
				response.getWriter().append("Emails do not match. <br />");
			}
			if (!password.equals(rePassword)) {
				response.getWriter().append("Passwords do not match. <br />");
			}
		}
		else {
			boolean userAdded = Security.addUser(email, password);
			
			// TODO test that it works + change results of success/failure as needed
			if (userAdded) {
				response.sendRedirect("/eBayLite/Login.html");
			}
			else {
				response.getWriter().append("Failed to Add User.");
				if(Security.doesUserExist(email)) {
					response.getWriter().append("<br />User already Exists.");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
