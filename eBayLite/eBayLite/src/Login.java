

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Security;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean validLogin = Security.authenticate(email, password);
		
		// TODO test that it works + change results of success/failure as needed
		response.setContentType("text/html");  
		if (validLogin) {
			HttpSession session = request.getSession();
            session.setAttribute("email", email);
            // Check if null with request.getSession().getAttribute("email") before accessing page with restricted access
			response.getWriter().append("<h3>Login Successful.</h3>");
			request.getRequestDispatcher("Login.html").include(request, response);
		}
		else {
			response.getWriter().append("<h3>Login Failed.</h3>");
			request.getRequestDispatcher("Login.html").include(request, response);  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
