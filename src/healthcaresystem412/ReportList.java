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
   
    public static ArrayList<Report> loadReportList(ArrayList<Integer> userIDArrayList) {
        // Initializes an ArrayList of reports to be returned to the calling class
        ArrayList<Report> listToReturn = new ArrayList<>();
        
        // Creates a loop to iterate and add to the ArrayList reports that are generated for each userID that may be viewed by the user
        for (int i = 0; i < userIDArrayList.size(); i++) {
            // SQL String to pull results from the report table by userID
            String SQL = "SELECT appuser.user_name,appuser.user_first_last,appuser.user_dob,report.report_id,report.report_name,report.report_body " + 
                    "FROM appuser " + 
                    "INNER JOIN report ON appuser.user_id=report.assigned_user_id " + 
                    "WHERE user_id = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                // Sets the "?" in the query to the userID we want to get reports for
                prepState.setInt(1, userIDArrayList.get(i));
                ResultSet results = prepState.executeQuery();
                
                // Iterates through the results of the query, creating a report and adding it to the list
                while (results.next()) {
                    Report reportToAdd = new Report(results.getLong("report_id"),
                            results.getString("report_name"),
                            results.getString("report_body"),
                            results.getString("user_name"),
                            results.getString("user_first_last"),
                            results.getString("user_dob"));

                    listToReturn.add(reportToAdd);
                }
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return listToReturn;
    }
        
}
