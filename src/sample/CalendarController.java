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

public class CalendarController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

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
        System.out.println(currentUser.getUserName());
        handleSceneChange("Appointment.fxml");

    }

    @FXML
    void handleEditAppointment(ActionEvent event) {
        // grab data from selected appointment
        // query data from database
        // pass data to Customer View
        // change to Appointment View Scene
        handleSceneChange("Appointment.fxml");
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
        handleSceneChange("CustomerView.fxml");
    }


    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

}
