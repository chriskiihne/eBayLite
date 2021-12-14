

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
 * Servlet implementation class Bidding
 */
@WebServlet("/Bidding")
public class Bidding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bidding() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Bidstring = request.getParameter("Bid");
		String intstring = request.getParameter("id");
	    int id = Integer.parseInt(intstring);
		int Bid = Integer.parseInt(Bidstring);
		Connection connection = null;
		String updateSql = "update items set CURRENTBID=" + Bid + " where id=" + id;
		try {
			DBConnection.getDBConnection();
	        connection = DBConnection.connection;
	        String selectSQL = "SELECT * FROM items WHERE id=" + id;
	        PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
	        ResultSet rs = preparedStmt.executeQuery();
	        int startBid = 0;
	        int currentBid = 0;
	        while (rs.next()) {
	        int itemid = rs.getInt("id");
	        String itemName = rs.getString("NAME").trim();
	        int startingBid = rs.getInt("STARTINGBID");
	        startBid = startingBid;
	        int currentBID = rs.getInt("CURRENTBID");
	        currentBid = currentBID;
	        String auctionStart = rs.getString("AUCTIONSTART").trim();
	        String auctionEnd = rs.getString("AUCTIONEND").trim();
	        int auctionLength = rs.getInt("AUCTIONLENGTH");
	        String description = rs.getString("DESCRIPTION").trim();
	        int auctioneerID = rs.getInt("AUCTIONEERID");
	        int highestBidderID = rs.getInt("HIGHESTBIDDERID");
	        }
	        if(Bid < startBid || Bid <= currentBid) {
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
	    				"		<section>\n" +
	    				"       <p> Either your Bid was lower then the starting bid or the current bid! Bid Higher!</p>");
	        }
	        else
	        {
	        	PreparedStatement preparedStmt2 = connection.prepareStatement(updateSql);
	        	preparedStmt2.execute();
	        	connection.close();
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
		    				"		<section>\n" +
		    				"       <p> Bid Placed! </p>");
	        }
		} catch(SQLException se) {
	    	se.printStackTrace();
	    } catch(Exception e) {
	    	e.printStackTrace();
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
