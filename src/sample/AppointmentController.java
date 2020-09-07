package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    // Editing or not
    private Boolean edit = false;

    // LINKED NODES
    @FXML
    private DatePicker datePicker;
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
    private TextArea description;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Customer> customerList;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private Label errorMessage;
    @FXML
    private Button cancelChange;
    @FXML
    private Button submitButton;

    // Lists for Customer Select
    private List<Customer> custViewList = new ArrayList<Customer>();
    private ObservableList<Customer> observableCustView = FXCollections.observableArrayList();

    //Lists for Time Select
    private ObservableList<Integer> hourSelect = FXCollections.observableArrayList();
    private ObservableList<String> minuteSelect = FXCollections.observableArrayList();
    private ObservableList<String> AMPM = FXCollections.observableArrayList();


    // METHODS
    @FXML
    void changeView(ActionEvent event) {

    }

    @FXML
    void handleSubmit(ActionEvent event){
        try {
            // declare all values
            LocalDate dateSelect;
            Integer year;
            Integer month;
            Integer date;
            Integer hour;
            Integer minute;
            Integer appId;
            String AMPM;
            Integer correctHour;
            String appTitle;
            String appLoc;
            String appType;
            String appContact;
            String appDescription;
            Customer c;
            Integer cid;

            // check if any fields are null or empty: throw exception if so

            if (datePicker.getValue() == null){
                throw new IllegalArgumentException("Please pick a date");
            } else if (timeStartHour.getSelectionModel().isEmpty()){
                throw new IllegalArgumentException("Please pick a starting hour");
            } else if (timeStartMinute.getSelectionModel().isEmpty()) {
                throw new IllegalArgumentException("Please pick a starting minute");
            } else if (timeStartAMPM.getSelectionModel().isEmpty()){
                throw new IllegalArgumentException("Please pick starting in the AM or PM");
            } else if (duration.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter an appointment duration");
            } else if (location.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter an appointment location");
            } else if (title.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter an appointment title");
            } else if (appointmentType.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter an appointment type");
            } else if (contact.getText().isEmpty()){
                throw new IllegalArgumentException("Please enter an appointment contact");
            } else if (description.getText().isEmpty()){
                throw new IllegalArgumentException("Please enter an appointment description");
            } else if (customerList.getSelectionModel().isEmpty()){
                throw new IllegalArgumentException("Please select a customer");
            } else {
                dateSelect = datePicker.getValue();
                year = dateSelect.getYear();
                month = dateSelect.getMonthValue();
                date = dateSelect.getDayOfMonth();
                hour = (Integer) timeStartHour.getValue();
                minute = Integer.parseInt((String) timeStartMinute.getValue());
                AMPM = (String) timeStartAMPM.getValue();
                correctHour = 0;
                appTitle = title.getText();
                appLoc = location.getText();
                appType = appointmentType.getText();
                appContact = contact.getText();
                appDescription = description.getText();
                c = customerList.getSelectionModel().getSelectedItem();
                cid = c.getCustomerId();
            }

            // outside business hours exception control
            if ((hour < 8 && AMPM == "AM") || (hour > 5 && AMPM == "PM" && hour != 12) || (hour == 12 && AMPM=="AM")){
                throw new IllegalArgumentException("Please choose an appointment within business hours");
            }

            // calculate 24 hour time value
            if (hour != 12 && AMPM == "PM"){
                correctHour = hour + 12;
            } else {
                correctHour = hour;
            }

            // calculate ending time from duration
            Integer dur = Integer.valueOf(duration.getText());

            Integer endminute = (minute + dur) % 60;
            Integer endhour = correctHour + ((minute+dur) / 60);

            // Construct ZonedDateTime object
            ZonedDateTime startdt = ZonedDateTime.of(year, month, date, correctHour, minute, 0, 0, ZoneId.systemDefault());
            ZonedDateTime enddt = ZonedDateTime.of(year, month, date, endhour, endminute, 0, 0, ZoneId.systemDefault());

            // Convert to UTC for query
            LocalDateTime startldt = LocalDateTime.ofInstant(startdt.toInstant(), ZoneOffset.UTC);
            LocalDateTime endldt = LocalDateTime.ofInstant(enddt.toInstant(), ZoneOffset.UTC);

            // Grab current users id
            Integer userid = currentUser.getUserID();

            // grab current date time for created date
            LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);


            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");

            if (edit) {
                    // grab appointmentID
                    appId = Integer.parseInt(appointmentID.getText());

                    // appointment overlap exception control
                    PreparedStatement checkAppointment = connection.prepareStatement("SELECT * FROM appointment WHERE (appointmentId IS NOT ?) AND (start BETWEEN ? AND ?) OR (? BETWEEN start AND end) OR (? BETWEEN start AND end)");
                    checkAppointment.setInt(1, appId);
                    checkAppointment.setObject(2, startldt);
                    checkAppointment.setObject(3, endldt);
                    checkAppointment.setObject(4, startdt);
                    checkAppointment.setObject(5, endldt);

                    ResultSet rs = checkAppointment.executeQuery();

                    if (rs.next()){
                        throw new IllegalArgumentException("There is an overlapping appointment.");
                    }


                    // Construct prepared statement
                    PreparedStatement appEdit = connection.prepareStatement("UPDATE appointment SET customerId = ?, userId = ?, title = ?, description = ?, location = ?, contact = ?, type = ?, start = ?, end = ?, lastUpdateBy = ?, url = ? WHERE appointmentId=?");
                    appEdit.setInt(1, cid);
                    appEdit.setInt(2, userid);
                    appEdit.setString(3, appTitle);
                    appEdit.setString(4, appDescription);
                    appEdit.setString(5, appLoc);
                    appEdit.setString(6, appContact);
                    appEdit.setString(7, appType);
                    appEdit.setObject(8, startldt);
                    appEdit.setObject(9, endldt);
                    appEdit.setString(10, currentUser.getUserName());
                    appEdit.setString(11, "not needed");
                    appEdit.setInt(12, appId);

                    // execute and close prepared statement
                    appEdit.execute();
                    appEdit.close();
                    connection.close();

            } else {

                    PreparedStatement checkAppointment = connection.prepareStatement("SELECT * FROM appointment WHERE (start BETWEEN ? AND ?) OR (? BETWEEN start AND end) OR (? BETWEEN start AND end)");
                    checkAppointment.setObject(1, startldt);
                    checkAppointment.setObject(2, endldt);
                    checkAppointment.setObject(3, startdt);
                    checkAppointment.setObject(4, endldt);

                    ResultSet rs = checkAppointment.executeQuery();

                    if (rs.next()){
                        rs.beforeFirst();
                        throw new IllegalArgumentException("There is an overlapping appointment.");
                    }

                    // Construct prepared statement
                    PreparedStatement appAdd = connection.prepareStatement("INSERT INTO appointment (customerId, userId, title, description, location, contact, type, start, end, createDate, createdBy, lastUpdateBy, url) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    appAdd.setInt(1, cid);
                    appAdd.setInt(2, userid);
                    appAdd.setString(3, appTitle);
                    appAdd.setString(4, appDescription);
                    appAdd.setString(5, appLoc);
                    appAdd.setString(6, appContact);
                    appAdd.setString(7, appType);
                    appAdd.setObject(8, startldt);
                    appAdd.setObject(9, endldt);
                    appAdd.setObject(10, now);
                    appAdd.setString(11, currentUser.getUserName());
                    appAdd.setString(12, currentUser.getUserName());
                    appAdd.setString(13, "not needed");

                    // execute and close prepared statement
                    appAdd.execute();
                    appAdd.close();
                    connection.close();

                     // change scene to Calendar
                    handleSceneChange("Calendar.fxml");
            }

        } catch (SQLException e) {
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e);
            errorMessage.setText("Duration must be an integer");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            errorMessage.setText(e.getMessage());
        }
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

        // set values for time select
        for (int i = 1; i <= 12; i++){
            hourSelect.add(i);
        }
        for (int i = 0; i <= 59; i++){
            minuteSelect.add(String.format("%02d", i));
        }
        AMPM.addAll("AM", "PM");

        // initialize choicebox values to observable lists
        timeStartHour.setItems(hourSelect);
        timeStartMinute.setItems(minuteSelect);
        timeStartAMPM.setItems(AMPM);

        try {
            // load in customers
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM customer");

            ResultSet results = statement.getResultSet();

            while (results.next()) {

                // Get customer table info
                Integer custid = results.getInt("customerId");
                String custName = results.getString("customerName");

                Customer c = new Customer(custid, custName);

                // Load to observable list
                custViewList.add(c);

            }

            results.close();

            statement.close();
            connection.close();

        } catch (SQLException e){
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        }

        observableCustView = FXCollections.observableArrayList(custViewList);
        customerList.setItems(observableCustView);

        // lambda function to clean up set function
        name.setCellValueFactory((p ->
                p.getValue().nameProperty()
        ));

        filterList();

    }

    void filterList() {
        // Wrap in Filtered List, lambda function to display all of the elements
        FilteredList<Customer> filteredCustomers = new FilteredList<>(observableCustView, p -> true);

        // Set Predicate
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCustomers.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searched = newValue.toLowerCase();

                if (customer.getCustomerName().toLowerCase().contains(searched)) {
                    return true;
                }
                return false;
            });
        });

        // Wrap and bind in SortedList
        SortedList<Customer> sortedCustomers = new SortedList<>(filteredCustomers);
        sortedCustomers.comparatorProperty().bind(customerList.comparatorProperty());

        // Add the sorted items to table
        customerList.setItems(sortedCustomers);

    }

    void loadAppointment(Appointment a){
        // Set Values
        datePicker.setValue(a.getDate());
        timeStartHour.setValue(a.getHour());
        timeStartMinute.setValue(a.getMinute());
        timeStartAMPM.setValue(a.getAMPM());
        duration.setText(String.valueOf(a.getDuration()));
        location.setText(a.getLocation());
        title.setText(a.getTitle());
        appointmentType.setText(a.getType());
        appointmentID.setText(String.valueOf(a.getAppointmentId()));
        contact.setText(a.getContact());
        description.setText(a.getDescription());

        // loop through customer table and select if customer ID matches
        for (Customer c : observableCustView) {
            if (c.getCustomerId() == a.getCustomerID()){
                customerList.getSelectionModel().select(c);
            }
        }
        edit = true;

    }

}
