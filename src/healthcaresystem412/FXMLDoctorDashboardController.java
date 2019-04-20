/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcaresystem412;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLDoctorDashboardController extends Controller implements Initializable {

    @FXML
    private Button BackButtonAction;
    @FXML
    private TableColumn<Report, String> bodyCol;

    @FXML
    private Button newReportButton;
    @FXML
    private TableView<Report> table;

//    public ObservableList<Report> list = FXCollections.observableArrayList(ReportList.reports);
    @FXML
    private TableColumn<Report, String> patientNameCol;
    @FXML
    private TableColumn<Report, String> titleCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ArrayList to store UserId's of who the main user may view
        ArrayList<Integer> userIDs = new ArrayList<>();

        // SQL Statment string to display who's reports the user may view
        String SQL = "SELECT appuser.user_id,doctor_to_patient_assignment.to_be_viewed_id "
                + "FROM appuser "
                + "INNER JOIN doctor_to_patient_assignment ON appuser.user_id=doctor_to_patient_assignment.viewer_id "
                + "WHERE user_id = ?";

        // Connection to the database to run query
        try (Connection sqlConnection = PostgresConnector.connect();
                PreparedStatement prepState = sqlConnection.prepareStatement(SQL)) {

            // Set the "?" in the SQL String to be the User's ID to run against the assignment table
            prepState.setInt(1, (int) Controller.user.getUserId());
            ResultSet results = prepState.executeQuery();

            // Steps through the results of the query, adding each userID the user may view to the ArrayList of userID's
            while (results.next()) {
                userIDs.add(results.getInt("to_be_viewed_id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Declares an ArrayList of reports that were loaded from the ReportList class
        ArrayList<Report> data = ReportList.loadReportList(userIDs);

        // Converts the data ArrayList into something JavaFX can use for table setting
        ObservableList<Report> list = FXCollections.observableArrayList(data);

        // Sets the values of the tables to be viewed
        patientNameCol.setCellValueFactory(new PropertyValueFactory<Report, String>("name"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Report, String>("reportTitle"));
        bodyCol.setCellValueFactory(new PropertyValueFactory<Report, String>("reportBody"));
        table.setItems(list);
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("Logged out");
        Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();

    }

    @FXML
    private void handleCreateAssignmentAction(ActionEvent event) throws IOException {
        System.out.println("Moving to Edit Patients");
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLEditPatients.fxml"));
        Scene editPatientScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(editPatientScene);
        window.show();
    }

    @FXML
    private void handleNewReportAction(ActionEvent event) throws IOException {
        System.out.println("Loading report creation");
        Parent reportParent = FXMLLoader.load(getClass().getResource("FXMLReportCreation.fxml"));
        Scene reportCreationScene = new Scene(reportParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(reportCreationScene);
        window.show();
    }

    @FXML
    private void handleExportAction(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Report Export");
        
        Report report = table.getSelectionModel().getSelectedItem();
        String selected = "";
        selected = "Report Title: " + report.getReportTitle()
                + "\nPatient: " + report.getUsername()
                + "\nReport Body: " + report.getReportBody();
        System.out.println(selected);

       

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            SaveFile(selected, file);
        }

        window.show();

    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            //   Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
