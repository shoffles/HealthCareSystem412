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
   
    public void loadReportList(int[] userIDArray) {
        for (int i = 0; i < userIDArray.length; i++) {
            
        
            String SQL = "SELECT appuser.user_name,appuser.user_first_last,appuser.user_dob,report.report_title,report.report_body " + 
                    "FROM appuser " + 
                    "INNER JOIN report ON appuser.user_id=report.assigned_user_id " + 
                    "WHERE user_id = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setInt(1, i);
                ResultSet results = prepState.executeQuery();

                while (results.next()) {
                    Report reportToAdd = new Report(results.getString("report_title"),
                            results.getString("report_body"),
                            results.getString("user_name"),
                            results.getString("user_first_last"),
                            results.getString("user_dob"));

                    this.reports.add(reportToAdd);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
        
}
