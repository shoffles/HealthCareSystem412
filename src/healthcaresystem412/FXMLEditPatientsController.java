/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import healthcaresystem412.PostgresConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author stevenweber
 */
public class FXMLEditPatientsController implements Initializable {

    @FXML
    private TextField viewerField;
    @FXML
    private TextField patientField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Back to Doctor Dashboard");
        Parent docDashParent = FXMLLoader.load(getClass().getResource("FXMLDoctorDashboard.fxml"));
        Scene docDashScene = new Scene(docDashParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(docDashScene);
        window.show();
    
    }
    
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        
      //  Report report = new Report(this.titleField.getText(), this.reportField.getText(), this.usernameField.getText(), this.nameField.getText(), this.ageField.getText());
        // Add to DB
        long viewerId = 0;
        long toBeViewedId = 0;
        
        // This block queries the appuser table to set a user ID for entry into the report field
        {
            String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                    "FROM appuser " +   
                    "WHERE user_name = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, this.viewerField.getText());
                
                // Results of the query are obtained
                ResultSet results = prepState.executeQuery();

                // Finds the userID from the query
                results.next();
                viewerId = results.getInt("user_id");
                
                
                
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // This block gets the userId of the person who can now be viewed by the user of the previous block
        {
            String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                    "FROM appuser " +   
                    "WHERE user_name = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, this.patientField.getText());
                
                // Results of the query are obtained
                ResultSet results = prepState.executeQuery();

                // Finds the userID from the query
                results.next();
                toBeViewedId = results.getInt("user_id");
                
                
                
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // This block inserts a report into the report table using the ID scraped from the Username search
        {
            String SQL = "INSERT INTO doctor_to_patient_assignment (viewer_id, to_be_viewed_id) " 
                    + "VALUES(?,?)";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                // Sets both values to the new ID
                prepState.setInt(1, (int) viewerId);
                prepState.setInt(2, (int) toBeViewedId);

                prepState.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
        System.out.println("Patient reassigned");
       Parent docDashParent = FXMLLoader.load(getClass().getResource("FXMLDoctorDashboard.fxml"));
        Scene docDashScene = new Scene(docDashParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(docDashScene);
        window.show();
    }
    
}
