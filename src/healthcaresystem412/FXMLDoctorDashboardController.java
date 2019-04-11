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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLDoctorDashboardController implements Initializable {

    @FXML
    private Button BackButtonAction;
    @FXML
    private TableColumn<Report, String> nameCol;

    @FXML
    private Button newReportButton;
    @FXML
    private TableView<Report> table;

    public ObservableList<Report> list = FXCollections.observableArrayList(ReportList.reports);
            
    @FXML
    private TableColumn<Report, String> idCol;
    @FXML
    private TableColumn<Report, String> titleCol;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Report,String>("name"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportTitle"));
        table.setItems(list);
        
        for(int i = 0; i < ReportList.reports.size(); i++) {
            System.out.println(ReportList.reports.get(i));
        }
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

    @FXML
    private void handleNewReportAction(ActionEvent event) throws IOException {
        System.out.println("Loading report creation");
        Parent reportParent = FXMLLoader.load(getClass().getResource("FXMLReportCreation.fxml"));
        Scene reportCreationScene = new Scene(reportParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reportCreationScene);
        window.show();
    }
    
}
