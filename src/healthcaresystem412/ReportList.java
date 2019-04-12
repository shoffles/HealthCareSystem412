/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Thomas
 */
public class ReportList {
   public static ArrayList<Report> reports = new ArrayList<>();
   
   public ReportList() {
      
       
   }
   
//    public void loadReportList(int[] userIDArray) {
//        for (int i = 0; i < userIDArray.length; i++) {
//            
//        
//            String SQL = "SELECT user_name,user_type,user_password,user_first_last,user_dob " + 
//                    "FROM appuser ";
//
//            try (Connection sqlConnection = PostgresConnector.connect();
//                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {
//
//                ResultSet results = prepState.executeQuery();
//
//                while (results.next()) {
//                    User userToAdd = new User(results.getString("user_name"),
//                            results.getString("user_type"),
//                            results.getString("user_password"),
//                            results.getString("user_first_last"),
//                            results.getString("user_dob"));
//
//                    this.report.put(userToAdd.getUsername(), userToAdd);
//                }
//
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
    
}
