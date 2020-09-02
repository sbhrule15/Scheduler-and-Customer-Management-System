package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Calendar implements Initializable {

    // LINKED NODES
    @FXML
    private Button addAppointment;
    @FXML
    private Button editAppointment;
    @FXML
    private Button deleteAppointment;
    @FXML
    private RadioButton weekView;
    @FXML
    private RadioButton monthView;
    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView weekTable;
    @FXML
    private TableColumn sundayColumn;
    @FXML
    private TableColumn mondayColumn;
    @FXML
    private TableColumn tuesdayColumn;
    @FXML
    private TableColumn wednesdayColumn;
    @FXML
    private TableColumn thursdayColumn;
    @FXML
    private TableColumn fridayColumn;
    @FXML
    private TableColumn saturdayColumn;

    @FXML
    private Button customerView;
    @FXML
    private Button exitChange;

    // METHODS

    @FXML
    void handleAddAppointment(ActionEvent event) {
        handleChangeScene("Appointment.fxml");
    }

    @FXML
    void handleEditAppointment(ActionEvent event) {
        // grab data from selected appointment
        // query data from database
        // pass data to Customer View
        // change to Appointment View Scene
        handleChangeScene("Appointment.fxml");
    }

    @FXML
    void handleDeleteAppointment(ActionEvent event) {
        // grab appointmentID from selected appointment
        // select from database with appointmentID
        // delete appointment from database
        // reload Table
    }



    @FXML
    void handleCustomerSceneChange(ActionEvent event) {
        handleChangeScene("CustomerView.fxml");
    }






    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChangeScene(String destination) {
        Parent main = null;
        try {
            main = FXMLLoader.load(getClass().getResource(destination));
            Scene scene = new Scene(main);

            Stage stage = Main.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
