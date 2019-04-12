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
        // SQL CODE TO ADD
        /*
        
            
        
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
*/
        for(int i = 0; i < ReportList.reports.size(); i++) {
            if (ReportList.reports.get(i).getUsername().equals(Controller.user.getUsername())) {
                data.add(ReportList.reports.get(i));
            }
        }
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
