/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Label username_label;
    @FXML
    private Label password_label;
    @FXML
    private TextField username_textfield;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button login_button;
    
    private final UserAuth users = new UserAuth();
    private final ReportList reports = new ReportList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginMouseClickHandler(MouseEvent event) throws IOException {
        if(this.username_textfield.getText().equals("") || this.password_field.getText().equals("")) {
            System.out.println("Please enter a username and password!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please enter a username and password!");
            alert.show();
        }
        else {
            String username = this.username_textfield.getText();
            if(users.checkUsernamePassword(username, this.password_field.getText())) {
                if(users.getUserType(username).equals("doctor")) {
                    System.out.println("Login Successful! Type: Doctor");
                    Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLDoctorDashboard.fxml"));
                    Scene loginScene = new Scene(loginParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(loginScene);
                    window.show();
                }
                else {
                    System.out.println("Login Successful! Type: Patient");
                    
                    /*
                     try {
                        //Load second scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPatientDashboard.fxml"));
                        Parent root = loader.load();

                        //Get controller of scene2
                        FXMLPatientDashboardController patientDashboardController = loader.getController();
                        //Pass whatever data you want. You can have multiple method calls here
                        patientDashboardController.setUser(users.getUser(username));

                        //Show scene 2 in new window            
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        //stage.setTitle("Second Window");
                        stage.show();
                    } 
                     catch (LoadException ex) {
                        System.err.println(ex);
                         System.out.println(ex.getStackTrace());
                    }
                    
`                   */
                    /*
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPatientDashboard.fxml"));
                    Parent root = loader.load();
                    FXMLPatientDashboardController controller = loader.getController();
                    System.out.println(users.getUser(username));
                    controller.setUser(users.getUser(username));
                    
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    */
                    //Works but wont display data
                    
                    Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLPatientDashboard.fxml"));
                    Scene loginScene = new Scene(loginParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(loginScene);
                    window.show();
                    
                    
                    
                }
                
            }
            else {
                System.out.println("Login Unsuccessful!");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Login unsuccessful!");
                alert.show();
            }
        }
    }
    
}
