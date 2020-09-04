package sample;

import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    // LINKED NODES
    @FXML
    private Label alertMessage;
    @FXML
    private Button editCustomer;
    @FXML
    private Button deleteCustomer;
    @FXML
    private Button addNewCustomer;
    @FXML
    private Button calendarChange;
    @FXML
    private Button exitChange;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Customer> customerList;
    @FXML
    private TableColumn<Customer, String> name;

    private List<Customer> custViewList = new ArrayList<Customer>();
    private ObservableList<Customer> observableCustView = FXCollections.observableArrayList();

    // METHODS
    @FXML
    void handleEditCustomer(ActionEvent event) {

        // Get selected customer
        Customer c = customerList.getSelectionModel().getSelectedItem();
        if (1 == 0) {
            alertMessage.setText("Please select a customer to edit");
        } else {
            handleSceneChange("Customer.fxml",true, c);
        }
    }

    @FXML
    void handleDeleteCustomer(ActionEvent event) {
        Integer cid = 0;
        Customer c = null;
        // grab customerID from selected customer
        try {
            c = customerList.getSelectionModel().getSelectedItem();
            cid = c.getCustomerId();
        } catch (NullPointerException e){
            alertMessage.setText("Please select a customer to delete");
        }

        if (c != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
                PreparedStatement delappstmt = connection.prepareStatement("DELETE FROM appointment WHERE customerId = ?");
                PreparedStatement delcusstmt = connection.prepareStatement("DELETE customer, address FROM customer INNER JOIN address WHERE customer.customerId = ? AND customer.addressId = address.addressId");

                delappstmt.setInt(1, cid);
                delcusstmt.setInt(1, cid);

                // delete query using customerID
                delappstmt.execute();
                delcusstmt.execute();

                // close out
                delappstmt.close();
                delcusstmt.close();
                connection.close();

                // Delete from list
                observableCustView.remove(c);

                // Display success message
                alertMessage.setText("Customer successfully deleted.");

            } catch (SQLException e){
                System.out.println("There was an error connecting to the database: " + e.getMessage());
            }

        }
    }
    @FXML
    void handleAddCustomer(ActionEvent event) {
        handleSceneChange("Customer.fxml", false, null);
    }

    @FXML
    void handleCalendarChange(ActionEvent event){
        handleSceneChange("Calendar.fxml", false, null);
    }

    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

    // SCENE CHANGE

    @FXML
    private void handleSceneChange(String destination, Boolean edit, Customer customer) {
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
            } else if (destination == "Customer.fxml" && edit){
                cusEditControllerLoad(loader, customer);
            } else if (destination == "Customer.fxml" && !(edit)){
                cusAddControllerLoad(loader);
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

    private void cusAddControllerLoad(FXMLLoader loader){
        CustomerController controller = loader.getController();
        controller.initUser(currentUser);
    }

    private void cusEditControllerLoad(FXMLLoader loader, Customer customer){
        CustomerController controller = loader.getController();
        controller.initUser(currentUser);
        controller.loadCustomerInfo(customer);
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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM customer");

            ResultSet results = statement.getResultSet();

            while (results.next()) {
                // Get customer table info

                Integer custid = results.getInt("customerId");
                String custName = results.getString("customerName");
                Integer custaddiD = results.getInt("addressId");
                Integer custact = results.getInt("active");
                String custcreated = results.getString("createDate");
                String custcreatedby = results.getString("createdBy");
                String custlastupdate = results.getString("lastUpdateBy");

                // get address info

                PreparedStatement addressstmt = connection.prepareStatement("SELECT * FROM address WHERE addressId = ?;");
                addressstmt.setInt(1, custaddiD);

                addressstmt.execute();
                ResultSet results2 = addressstmt.getResultSet();

                // load address info into variables for customer object
                while (results2.next()) {
                    String custadd = results2.getString("address");
                    String custadd2 = results2.getString("address2");
                    Integer custcityid = results2.getInt("cityId");
                    Integer custpostal = results2.getInt("postalCode");
                    String custphone = results2.getString("phone");

                    // build customer object
                    Customer c = new Customer(custid, custName, custaddiD, custcreated, custcreatedby, custphone, custadd, custadd2, custcityid, custpostal);

                    // add to list of customers
                    custViewList.add(c);

                }
            }

            results.close();

            statement.close();
            connection.close();

        } catch (SQLException e) {
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

    private void filterList() {
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

}

