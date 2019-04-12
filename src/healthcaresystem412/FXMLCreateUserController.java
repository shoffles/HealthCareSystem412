/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLCreateUserController implements Initializable {

    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField dobField;

    private UserAuth users = new UserAuth();
    @FXML
    private ChoiceBox<String> userChoice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] options = {"Doctor", "Patient"};
        ObservableList<String> list = FXCollections.observableArrayList(options);
        userChoice.setItems(list);
    }    

    @FXML
    private void handleSubmitButton(ActionEvent event) throws IOException {
        
        if(users.addMapping(usernameField.getText(), passwordField.getText(), userChoice.getValue().toString(), nameField.getText(), dobField.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Account created!");
            alert.show();
            Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username already taken, try again!");
            alert.show();
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
}
