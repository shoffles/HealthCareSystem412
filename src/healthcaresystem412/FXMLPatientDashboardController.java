/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLPatientDashboardController implements Initializable {

    @FXML
    private Button BackButtonAction;
    @FXML
    private TableColumn<Report, String> idCol;
    @FXML
    private TableColumn<Report, String> titleCol;
    @FXML
    private TableColumn<Report, String> dateCol;
    
    private User user;
    @FXML
    private TableView<Report> table;
    @FXML
    private Label testLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<Report> list = FXCollections.observableArrayList(ReportList.reports);
        
        idCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportData"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportTitle"));
        table.setItems(list);
        

    }    

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Logged out");
        Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    
    }
    
    public void setUser(User user) {
        this.user = user;
        System.out.println("IT FUCKING SAVED");
    }
    
}
