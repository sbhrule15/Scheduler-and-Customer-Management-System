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

public class AppointmentController implements Initializable {

    // LINKED NODES
    @FXML
    private DatePicker dateSelect;
    @FXML
    private ChoiceBox timeStartHour;
    @FXML
    private ChoiceBox timeStartMinute;
    @FXML
    private ChoiceBox timeStartAMPM;
    @FXML
    private TextField duration;
    @FXML
    private TextField location;
    @FXML
    private TextField title;
    @FXML
    private TextField appointmentType;
    @FXML
    private TextField appointmentID;
    @FXML
    private TextField contact;
    @FXML
    private TextField customerSearch;
    @FXML
    private TableView customerList;
    @FXML
    private TableColumn customerLastName;
    @FXML
    private TableColumn customerFirstName;
    @FXML
    private Button cancelChange;
    @FXML
    private Button createAppointment;

    // METHODS

    @FXML
    void handleCreateAppointment(ActionEvent event){
        // grab data from nodes
        // create appointment object
        // SQL insert with appointment object
        // change scene to Calendar
        handleSceneChange("Calendar.fxml");
    }

    @FXML
    void handleCancel(ActionEvent event) {
        handleSceneChange("Calendar.fxml");
    }


    // SCENE CHANGE METHOD

    @FXML
    private void handleSceneChange(String destination) {
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
