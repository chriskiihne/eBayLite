package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Security {
   private static Connection connection = null;
	
   public static boolean addUser(String email, String password) {
      if (!doesUserExist(email))
      {
         String insertSql = "INSERT INTO users (id, EMAIL, PASSWORD) values (default, ?, ?)";
         try {
            connection = DBConnection.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);
            preparedStmt.execute();
            preparedStmt.close();
            connection.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
         return doesUserExist(email);
      }
   	
      return false;
   }
	
   public static boolean authenticate(String email, String password) {
      return doesUserExist(email) && hash(getPassword(email)).equals(hash(password));
   }
	
   public static boolean doesUserExist(String email) {
      String selectSql =  " SELECT * FROM users WHERE EMAIL=?";
      boolean found = false;
   
      try {
         connection = DBConnection.getConnection();
         PreparedStatement preparedStmt = connection.prepareStatement(selectSql);
         preparedStmt.setString(1, email);
         ResultSet results = preparedStmt.executeQuery();
         found = results.isBeforeFirst();
         preparedStmt.close();
         results.close();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   	
      return found;
   }
	
   public static String getPassword(String email) {
      String selectSql =  " SELECT PASSWORD FROM users WHERE EMAIL=?";
      String password = null;
   
      try {
         connection = DBConnection.getConnection();
         PreparedStatement preparedStmt = connection.prepareStatement(selectSql);
         preparedStmt.setString(1, email);
         ResultSet results = preparedStmt.executeQuery();
      	
         while(results.next())
         {
            String result = results.getString("PASSWORD").trim();
            if (result != null && !result.isEmpty())
            {
               password = result;
            }
         }
      	
         preparedStmt.close();
         results.close();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   	
      return password;
   }
	
   private static String hash(String string) {
   	// TODO implement?
      return string;
   }
}
