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
public class FXMLPatientDashboardController extends Controller implements Initializable {

    @FXML
    private Button BackButtonAction;
    @FXML
    private TableColumn<Report, String> idCol;
    @FXML
    private TableColumn<Report, String> titleCol;
    @FXML
    private TableColumn<Report, String> dateCol;
    
    @FXML
    private TableView<Report> table;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        System.out.println(Controller.user.getUsername());
        ArrayList<Report> data = new ArrayList();
        ArrayList<Integer> userIDs = new ArrayList();
        // SQL CODE TO ADD
        
        
            
        
            String SQL = "SELECT appuser.user_id,doctor_to_patient_assignment.to_be_viewed_id " + 
                    "FROM appuser " + 
                    "INNER JOIN doctor_to_patient_assignment ON appuser.user_id=doctor_to_patient_assignment.viewer_id " + 
                    "WHERE user_id = ?";

            try (Connection sqlConnection = PostgresConnector.connect();
                    PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

                prepState.setInt(1, (int) Controller.user.getUserId());
                ResultSet results = prepState.executeQuery();
                
                
                while (results.next()) {
                    userIDs.add(results.getInt("to_be_viewed_id"));
                    System.out.println(results.getInt("to_be_viewed_id"));

                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
            data = ReportList.loadReportList(userIDs);
    

//        for(int i = 0; i < ReportList.reports.size(); i++) {
//            if (ReportList.reports.get(i).getUsername().equals(Controller.user.getUsername())) {
//                data.add(ReportList.reports.get(i));
//            }
//        }
        ObservableList<Report> list = FXCollections.observableArrayList(data);
        
        idCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Report,String>("reportDate"));
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
    
    
    
}
