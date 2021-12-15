import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;

/**
 * Servlet implementation class Homepage
 */
@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homepage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		display(request, response);
	}
	void display(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		out.println("<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"ISO-8859-1\">\n" + 
				"<style>\n" + 
				"header {\n" + 
				"	background-color: green;\n" + 
				"	color: white;\n" + 
				"	text-align: center;\n" + 
				"	padding: 5px;\n" + 
				"}\n" + 
				"nav {\n" + 
				"	line-height: 30px;\n" + 
				"	background-color: #eeeeee;\n" + 
				"	height: 300px;\n" + 
				"	width: 100px;\n" + 
				"	float: Left;\n" + 
				"	padding: 5px;\n" + 
				"}\n" + 
				"a {\n" + 
				"	display: block;\n" + 
				"}\n" + 
				"section {\n" + 
				"	width: 350px;\n" + 
				"	float: Left;\n" + 
				"	padding: 10px;\n" +
				"}\n" + 
				"footer {\n" + 
				"	background-color: green;\n" + 
				"	color: white;\n" + 
				"	clear: both;\n" + 
				"	text-align: center;\n" + 
				"	padding: 5px;\n" + 
				"}\n" + 
				"</style>\n" + 
				"<title>Ebay Lite</title>\n" + 
				"</head>\n" + 
				"	<body>\n" + 
				"		<header>\n" + 
				"			<h1> Ebay Lite Home </h1>\n" + 
				"		</header>\n" + 
				"		<nav>\n" + 
				"			<a href=\"/eBayLite/Login.html\">Login</a>\n" + 
				"			<a href=\"/eBayLite/Auctioning.html\">Auctioning</a>\n" + 
				"			<a href=\"/eBayLite/Logout\">Logout</a>\n" + 
				"		</nav>\n" + 
				"		<section>\n" + 
				"			<h1> Items </h1>\n" + 
				"			<p> If you are interested please search for an item below. </p>");
		try {
			DBConnection.getConnection();
			connection = DBConnection.getConnection();
			String selectSQL = "SELECT * FROM items";
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String auctionStart = rs.getString("auctionstart");
				String auctionEnd = rs.getString("auctionend");
				out.println("<a href=\"ItemView?item=" + id + "\" id=\"" + id + "\">" + name + " " + auctionStart + " " + auctionEnd + "</a>");
			}
			rs.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se2) {
		}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		out.println("</section>\n" + 
				"		<footer>\n" + 
				"		Copyright\n" + 
				"		</footer>\n" + 
				"		</body>\n" + 
				"		</html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

