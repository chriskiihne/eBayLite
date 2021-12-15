

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
 * Servlet implementation class ItemView
 */
@WebServlet("/ItemView")
public class ItemView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemView() {
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
		String url = request.getScheme() + "://" +
	             request.getServerName() + 
	             ":" +
			     request.getServerPort() + 
			     request.getRequestURI() +
			     "?" +
			     request.getQueryString();
	    String idString = url.substring(url.indexOf("item=") + 5);
	    int id = Integer.parseInt(idString);
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
				"		<h1> Ebay Lite </h1>\n" + 
				"		</header>\n" + 
				"		<nav>\n" + 
				"		<a href=\"Homepage\">Home</a>\n" + 
				"		</nav>\n" + 
				"		<section>\n");
		try {
	         connection = DBConnection.getConnection();
	         String selectSQL = "SELECT * FROM items WHERE id=" + id;
	         
	         preparedStatement = connection.prepareStatement(selectSQL);
	     
	         ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            int itemid = rs.getInt("id");
	            String itemName = rs.getString("NAME").trim();
	            int startingBid = rs.getInt("STARTINGBID");
	            int currentBID = rs.getInt("CURRENTBID");
	            String auctionStart = rs.getString("AUCTIONSTART").trim();
	            String auctionEnd = rs.getString("AUCTIONEND").trim();
	            int auctionLength = rs.getInt("AUCTIONLENGTH");
	            String description = rs.getString("DESCRIPTION").trim();
	            int auctioneerID = rs.getInt("AUCTIONEERID");
	            int highestBidderID = rs.getInt("HIGHESTBIDDERID");
	            out.println("<p> ID: " + itemid + "</p>\n");
	            out.println("<p> Name: " + itemName + "</p>\n");
	            out.println("<p> StartingBid: " + startingBid + "</p>\n");
	            out.println("<p> CurrentBid: " + currentBID + "</p>\n");
	            out.println("<p> auctionStart: " + auctionStart + "</p>\n");
	            out.println("<p> auctionEnd: " + auctionEnd + "</p>\n");
	            out.println("<p> auctionLength: " + auctionLength + "</p>\n");
	            out.println("<p> description: " + description + "</p>\n");
	            out.println("<p> auctioneerID: " + auctioneerID + "</p>\n");
	            out.println("<p> highestBidderID: " + highestBidderID + "</p>\n");
	            out.println("<form action=\"Bidding\" method=\"POST\">");
	            out.println("Bid: <input type=\"text\" name=\"Bid\"> <br />");
	            out.println("Confirm with item id: <input type=\"text\" name=\"id\"> <br />");
	            out.println("<input type=\"submit\" value=\"Submit\" />");
	            out.println("</form>");
	            }
				out.println("</section>\n" + 
				"	<footer>\n" + 
				"		Copyright\n" + 
				"	</footer>\n" + 
				"</body>\n" + 
				"</html>");
		rs.close();
		preparedStatement.close();
		connection.close();
	    } catch(SQLException se) {
	    	se.printStackTrace();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (preparedStatement != null)
	    			preparedStatement.close();
	    	} catch(SQLException se2) {
	    	}
	    	try {
	    		if(connection != null)
	    			connection.close();
	    	} catch(SQLException se) {
	    		se.printStackTrace();
	    	}
	    }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
