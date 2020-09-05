package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.Date;

public class CalendarController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    // Getting current date and time
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    Instant instant = Instant.now();

    // LINKED NODES
    @FXML
    private Label errorMessage;
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
    private TableView sundayTable;
    @FXML
    private TableView mondayTable;
    @FXML
    private TableView tuesdayTable;
    @FXML
    private TableView wednesdayTable;
    @FXML
    private TableView thursdayTable;
    @FXML
    private TableView fridayTable;
    @FXML
    private TableView saturdayTable;
    @FXML
    private TableColumn <Appointment, String> sundayColumn;
    @FXML
    private TableColumn <Appointment, String> mondayColumn;
    @FXML
    private TableColumn <Appointment, String> tuesdayColumn;
    @FXML
    private TableColumn <Appointment, String> wednesdayColumn;
    @FXML
    private TableColumn <Appointment, String> thursdayColumn;
    @FXML
    private TableColumn <Appointment, String> fridayColumn;
    @FXML
    private TableColumn <Appointment, String> saturdayColumn;

    @FXML
    private Button customerView;
    @FXML
    private Button exitChange;

    // DAY OF WEEK LISTS
    private ObservableList<Appointment> sundayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> mondayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> tuesdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> wednesdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> thursdayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> fridayList = FXCollections.observableArrayList();
    private ObservableList<Appointment> saturdayList = FXCollections.observableArrayList();

    // METHODS

    @FXML
    void handleAddAppointment(ActionEvent event) {
        System.out.println(currentUser.getUserName());
        handleSceneChange("Appointment.fxml", false, null);

    }

    @FXML
    void handleEditAppointment(ActionEvent event) {
        // grab appointment from selected appointment
        Appointment a1 = (Appointment) sundayTable.getSelectionModel().getSelectedItem();
        Appointment a2 = (Appointment) mondayTable.getSelectionModel().getSelectedItem();
        Appointment a3 = (Appointment) tuesdayTable.getSelectionModel().getSelectedItem();
        Appointment a4 = (Appointment) wednesdayTable.getSelectionModel().getSelectedItem();
        Appointment a5 = (Appointment) thursdayTable.getSelectionModel().getSelectedItem();
        Appointment a6 = (Appointment) fridayTable.getSelectionModel().getSelectedItem();
        Appointment a7 = (Appointment) saturdayTable.getSelectionModel().getSelectedItem();

        if (a1 != null){
            handleSceneChange("Appointment.fxml", true, a1);
        } else if (a2 != null){
            handleSceneChange("Appointment.fxml", true, a2);
        } else if (a3 != null){
            handleSceneChange("Appointment.fxml", true, a3);
        } else if (a4 != null){
            handleSceneChange("Appointment.fxml", true, a4);
        } else if (a5 != null){
            handleSceneChange("Appointment.fxml", true, a5);
        } else if (a6 != null){
            handleSceneChange("Appointment.fxml", true, a6);
        } else if (a7 != null){
            handleSceneChange("Appointment.fxml", true, a7);
        } else {
            errorMessage.setText("No appointment selected: Please select an appointment");
        }
    }

    @FXML
    void handleDeleteAppointment(ActionEvent event) {
        // grab appointmentID from selected appointment
        Appointment a1 = (Appointment) sundayTable.getSelectionModel().getSelectedItem();
        Appointment a2 = (Appointment) mondayTable.getSelectionModel().getSelectedItem();
        Appointment a3 = (Appointment) tuesdayTable.getSelectionModel().getSelectedItem();
        Appointment a4 = (Appointment) wednesdayTable.getSelectionModel().getSelectedItem();
        Appointment a5 = (Appointment) thursdayTable.getSelectionModel().getSelectedItem();
        Appointment a6 = (Appointment) fridayTable.getSelectionModel().getSelectedItem();
        Appointment a7 = (Appointment) saturdayTable.getSelectionModel().getSelectedItem();

        if (a1 != null){
            deleteSelected(a1);
            sundayList.remove(a1);
        } else if (a2 != null){
            deleteSelected(a2);
            mondayList.remove(a2);
        } else if (a3 != null){
            deleteSelected(a3);
            tuesdayList.remove(a3);
        } else if (a4 != null){
            deleteSelected(a4);
            wednesdayList.remove(a4);
        } else if (a5 != null){
            deleteSelected(a5);
            thursdayList.remove(a5);
        } else if (a6 != null){
            deleteSelected(a6);
            fridayList.remove(a6);
        } else if (a7 != null){
            deleteSelected(a7);
            saturdayList.remove(a7);
        } else {
            errorMessage.setText("No appointment selected: Please select an appointment");
        }
    }

    @FXML
    void getWeekDates(ActionEvent event) {
        // Clear out array lists
        sundayList.clear();
        mondayList.clear();
        tuesdayList.clear();
        wednesdayList.clear();
        thursdayList.clear();
        fridayList.clear();
        saturdayList.clear();

        // Set array for dates
        ArrayList<Date> datesInWeek = new ArrayList<>();

        // Get selected Date from Date Picker
        LocalDate dateSelect = datePicker.getValue();
        Date date = Date.from(dateSelect.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Set Calendar object to picked date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Loop through week and store values into Array
        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
            calendar.set(Calendar.DAY_OF_WEEK, i);
            datesInWeek.add(calendar.getTime());
        }

        // Check System Log of Dates Array
        for(int i = 0; i < datesInWeek.size(); i++){
            System.out.println(datesInWeek.get(i));
        }

        // Query each day of the week and load into correct column
        for(int i = 0; i < datesInWeek.size(); i++) {
            try {
                // Get connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");

                // Prepare statement using loop value
                PreparedStatement dayQuery = connection.prepareStatement("SELECT * FROM appointment WHERE DATE(start)=?");
                dayQuery.setObject(1, datesInWeek.get(i));

                // execute Statement
                ResultSet rs = dayQuery.executeQuery();

                // construct Appointment object with result set
                while (rs.next()){
                    Integer appointmentid =  rs.getInt("appointmentId");
                    Integer customerid = rs.getInt("customerId");
                    Integer userid = rs.getInt("userId");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String location = rs.getString("location");
                    String contact = rs.getString("contact");
                    String type = rs.getString("type");

                    // Date Conversions
                    LocalDateTime returnedDate = rs.getTimestamp("start").toLocalDateTime();
                    ZonedDateTime zdt = returnedDate.atZone(ZoneId.systemDefault());

                    LocalDateTime returnedDateEnd = rs.getTimestamp("end").toLocalDateTime();
                    ZonedDateTime zdt2 = returnedDateEnd.atZone(ZoneId.systemDefault());

                    // Calculate duration between start and end
                    Duration d = Duration.between(zdt, zdt2);

                    // Cast to Integer value
                    Integer dminutes = Math.toIntExact(d.toMinutes());

                    // Construct Appointment Object
                    Appointment a = new Appointment(appointmentid, customerid, userid, title, description, location, contact, type, zdt, dminutes);

                    switch (i){
                        case 0: sundayList.add(a);
                                break;
                        case 1: mondayList.add(a);
                                break;
                        case 2: tuesdayList.add(a);
                                break;
                        case 3: wednesdayList.add(a);
                                break;
                        case 4: thursdayList.add(a);
                                break;
                        case 5: fridayList.add(a);
                                break;
                        case 6: saturdayList.add(a);
                                break;
                    }
                }
                // close out connections
                dayQuery.close();
                connection.close();

            } catch (SQLException e){
                System.out.println("There was an error connecting to the database: " + e.getMessage());
            }
        }

        // set items to columns
        sundayTable.setItems(sundayList);
        mondayTable.setItems(mondayList);
        tuesdayTable.setItems(tuesdayList);
        wednesdayTable.setItems(wednesdayList);
        thursdayTable.setItems(thursdayList);
        fridayTable.setItems(fridayList);
        saturdayTable.setItems(saturdayList);

        // set cell value factories with lambda functions
        sundayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        mondayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        tuesdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        wednesdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        thursdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        fridayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));
        saturdayColumn.setCellValueFactory((param ->
                param.getValue().getAppointmentTitle()
        ));

    }


    @FXML
    void handleCustomerSceneChange(ActionEvent event) {
        handleSceneChange("CustomerView.fxml", false, null);
    }


    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleSceneChange(String destination, Boolean edit, Appointment appointment) {
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
            } else if (destination == "Appointment.fxml" && edit){
                appAddControllerLoad(loader, appointment);
            } else if (destination == "Appointment.fxml" && !(edit)){
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

    private void appAddControllerLoad(FXMLLoader loader, Appointment a){
        AppointmentController controller = loader.getController();
        controller.initUser(currentUser);
        controller.loadAppointment(a);
    }

    private void calControllerLoad(FXMLLoader loader){
        CalendarController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void deleteSelected(Appointment a){
        Integer aid = a.getAppointmentId();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
            ps.setInt(1, aid);

            ps.execute();

            ps.close();
            connection.close();

        } catch (SQLException e){
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        }
    }
}
