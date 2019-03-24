/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label username_label;
    @FXML
    private Label password_label;
    @FXML
    private TextField username_textfield;
    @FXML
    private PasswordField password_field;
    
    private User users = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void loginMouseClickHandler(MouseEvent event) {
        if(this.username_textfield.getText().equals("") || this.password_field.getText().equals("")) {
            System.out.println("Please enter a username and password!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please enter a username and password!");
            alert.show();
        }
        else {
            if(users.checkUsernamePassword(this.username_textfield.getText(), this.password_field.getText())) {
                System.out.println("Login Successful! ");
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Login Successful!");
                alert.show();
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
