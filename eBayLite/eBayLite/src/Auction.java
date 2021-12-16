import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

/**
 * Servlet implementation class Auctioning
 */
@WebServlet("/Auction")
public class Auction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Auction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String itemName = request.getParameter("ItemName");
		System.out.println("item name is: " + itemName);
		String description = request.getParameter("Description");
		System.out.println("item description is: " + description);

		String startingBid = request.getParameter("StartingBid");
		System.out.println("starting bid is: " + startingBid);

		String lengthOfAuction = request.getParameter("AuctionLength");
		System.out.println("length of auction is: " + lengthOfAuction);

		LocalDate date = LocalDate.now();
		String auctionStart = date.toString();
		System.out.println("auction start date is: " + auctionStart);
		String auctionEnd = date.plusDays(Integer.valueOf(lengthOfAuction)).toString();
		System.out.println("auction end date is: " + auctionEnd);
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		System.out.println("user email is: " + email);
		String auctioneerID = "";
		String getID = "SELECT ID from users WHERE (email = '" + email + "');";
		Connection connection = null;
		try {
			DBConnection.getConnection();
			connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(getID);
			ResultSet rs = ps.executeQuery(getID);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        String columnValue = rs.getString(i);
			        auctioneerID = columnValue;
			    }
			    System.out.println("");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection = null;
		String insertSql = "INSERT INTO items (id, NAME, STARTINGBID, CURRENTBID, AUCTIONSTART, AUCTIONEND, AUCTIONLENGTH, DESCRIPTION, AUCTIONEERID, HIGHESTBIDDERID) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			DBConnection.getConnection();
			connection = DBConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
			preparedStmt.setString(1, itemName);
			preparedStmt.setString(2, startingBid);
			preparedStmt.setInt(3, 0);
			preparedStmt.setString(4, auctionStart);
			preparedStmt.setString(5, auctionEnd);
			preparedStmt.setString(6, lengthOfAuction);
			preparedStmt.setString(7, description);
			preparedStmt.setString(8, auctioneerID); 
			preparedStmt.setInt(9, 0);
			preparedStmt.execute();
			preparedStmt.close();
			response.getWriter().append("<h3>Item succesfully added for auction.</h3>");
			request.getRequestDispatcher("Auctioning.html").include(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response.getWriter().append("<h3>An error has occured with auctioning your item.</h3>");
			request.getRequestDispatcher("Auctioning.html").include(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}