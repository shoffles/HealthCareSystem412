/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLReportCreationController implements Initializable {

    @FXML
    private Button searchForUserButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField usernameSearchField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea reportField;
    
    private String globalUsername;
    @FXML
    private TextField usernameField;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleSearchForUser(ActionEvent event) {
        String username = usernameSearchField.getText();
        User user;
        try {
            user = UserAuth.map.get(username);
            this.nameField.setText(user.getName());
            this.ageField.setText(user.getDOB());
            this.usernameField.setText(user.getUsername());
        }
        catch(Exception e) {
            System.out.println("Couldnt find user");
            this.nameField.clear();
            this.ageField.clear();
            this.usernameField.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User not found, try again or input manually.");
            alert.show();
        }
    }

    @FXML
    private void handleSubmitButton(ActionEvent event) throws IOException {
        
        Report report = new Report(this.titleField.getText(), this.reportField.getText(), this.usernameField.getText(), this.nameField.getText(), this.ageField.getText());
        // Add to DB
        long assignedUserId = 0;
        
        // This block queries the appuser table to set a user ID for entry into the report field
        {
            String SQL = "SELECT user_id,user_name,user_type,user_password,user_first_last,user_dob " + 
                    "FROM appuser " +   
                    "WHERE user_name = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setString(1, report.getUsername());
                
                // Results of the query are obtained
                ResultSet results = prepState.executeQuery();

                System.out.println("Get ID query executed");
                // Iterates through the results, creating users and adding them to the map
                results.next();
                assignedUserId = results.getInt("user_id");
                
                System.out.println("UserID for next query assigned");
                
                
                

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // This block inserts a report into the report table using the ID scraped from the Username search
        {
            String SQL = "INSERT INTO report(assigned_user_id,report_name,report_body) " 
                    + "VALUES(?,?,?)";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                // Sets both values to the new ID
                prepState.setInt(1, (int) assignedUserId);
                prepState.setString(2, report.getReportTitle());
                prepState.setString(3, report.getReportBody());

                prepState.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Report added!");
        alert.show();
        
        
        Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLDoctorDashboard.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
        
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLDoctorDashboard.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    
    

}
