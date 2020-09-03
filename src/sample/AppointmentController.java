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

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(destination));
            main = loader.load();
            Scene scene = new Scene(main);

            Stage stage = Main.getStage();
            stage.setScene(scene);

            // Pass User Object to appropriate Controller
            if (destination == "CustomerView.fxml"){
                cusViewControllerLoad(loader);
            } else if (destination == "Customer.fxml"){
                cusControllerLoad(loader);
            } else if (destination == "Appointment.fxml"){
                appControllerLoad(loader);
            } else if (destination == "Calendar.fxml") {
                calControllerLoad(loader);
            }

            // Show scene
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    // Controller User Passthrough Methods

    private void cusViewControllerLoad(FXMLLoader loader){
        CustomerViewController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void cusControllerLoad(FXMLLoader loader){
        CustomerController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void appControllerLoad(FXMLLoader loader){
        AppointmentController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void calControllerLoad(FXMLLoader loader){
        CalendarController controller = loader.getController();
        controller.initUser(currentUser);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
