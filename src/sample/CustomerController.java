package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;

import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    //Current User
    private User currentUser;

    // Editing or not
    private Boolean edit = false;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    // Address Id
    private Integer addressId;

    @FXML
    private Label headerLabel;
    @FXML
    private TextField customerID;
    @FXML
    private TextField name;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField addressLine1;
    @FXML
    private TextField addressLine2;
    @FXML
    private ChoiceBox citySelect;
    @FXML
    private TextField postalCode;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label alertMessage;


    @FXML
    void handleBack(ActionEvent event){
        handleSceneChange("CustomerView.fxml");
    }

    @FXML
    void handleSubmit(ActionEvent event) {

        // declare variables

        LocalDateTime currentdatetime = LocalDateTime.now();
        String citychoice;
        String custname;
        String custemail;
        String custadd1;
        String custadd2;
        String custphone;
        Integer custpostcode;
        Integer custcity;
        String createdByUsername;
        String lastUpdateBy;

        //exception control
        try {
            if (name.getText().isEmpty()){
                throw new IllegalArgumentException("Please enter a name");
            } else if (emailAddress.getText().isEmpty()){
                throw new IllegalArgumentException("Please enter an email address");
            } else if (phoneNumber.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter a phone number");
            } else if (addressLine1.getText().isEmpty()){
                throw new IllegalArgumentException("Please enter an address");
            } else if (citySelect.getSelectionModel().isEmpty()) {
                throw new IllegalArgumentException("Please choose a city");
            } else if (postalCode.getText().isEmpty()) {
                throw new IllegalArgumentException("Please enter a postal code");
            } else {
                citychoice = (String) citySelect.getValue();
                custname = name.getText();
                custemail = emailAddress.getText();
                custadd1 = addressLine1.getText();
                custadd2 = addressLine2.getText();
                custphone = phoneNumber.getText();
                custpostcode = Integer.parseInt(postalCode.getText());
                custcity = 0;
                createdByUsername = currentUser.getUserName();
                lastUpdateBy = currentUser.getUserName();
            }

            if (citychoice == "New York"){
                custcity = 1;
            }
            else if (citychoice == "Los Angeles"){
                custcity = 2;
            }
            else if (citychoice == "Toronto"){
                custcity = 3;
            }
            else if (citychoice == "Vancouver"){
                custcity = 4;
            }
            else if (citychoice == "Oslo"){
                custcity = 5;
            }

            // Initialize connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");

            if (edit){
                // Get customer ID
                Integer custId = Integer.valueOf(customerID.getText());

                // Prepare edit address statement
                PreparedStatement addressstmt = connection.prepareStatement("UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdateBy = ? WHERE addressId = ?", Statement.RETURN_GENERATED_KEYS);
                addressstmt.setString(1, custadd1);
                addressstmt.setString(2, custadd2);
                addressstmt.setInt(3, custcity);
                addressstmt.setInt(4, custpostcode);
                addressstmt.setString(5, custphone);
                addressstmt.setObject(6, lastUpdateBy);
                addressstmt.setInt(7, addressId);

                // Prepare edit customer statement
                PreparedStatement custstmt = connection.prepareStatement("UPDATE customer SET customerName = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?");
                custstmt.setString(1, custname);
                custstmt.setObject(2, currentdatetime);
                custstmt.setObject(3, lastUpdateBy);
                custstmt.setInt(4, custId);

                // Execute updates
                addressstmt.execute();
                custstmt.execute();

                // Close statements and connection
                addressstmt.close();
                custstmt.close();
                connection.close();

                handleSceneChange("CustomerView.fxml");

            } else {

                // Prepare insert or edit address statement
                PreparedStatement addressstmt = connection.prepareStatement("INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdateBy) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                addressstmt.setString(1, custadd1);
                addressstmt.setString(2, custadd2);
                addressstmt.setInt(3, custcity);
                addressstmt.setInt(4, custpostcode);
                addressstmt.setString(5, custphone);
                addressstmt.setObject(6, currentdatetime);
                addressstmt.setString(7, createdByUsername);
                addressstmt.setObject(8, lastUpdateBy);

                // Execute address insert and grab generated key
                addressstmt.execute();

                // Get address id
                ResultSet rs = addressstmt.getGeneratedKeys();

                if(rs.next()) {

                    Integer addresskey = rs.getInt(1);
                    System.out.println("Key Generated: " + addresskey);


                    // Prepare insert customer statement
                    PreparedStatement custstmt = connection.prepareStatement("INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdateBy) VALUES (?,?,?,?,?,?)");
                    custstmt.setString(1, custname);
                    custstmt.setInt(2, addresskey);
                    custstmt.setInt(3, custcity);
                    custstmt.setObject(4, currentdatetime);
                    custstmt.setString(5, "test");
                    custstmt.setObject(6, "test");

                    custstmt.execute();

                    custstmt.close();
                    connection.close();
                }
            }
        } catch(SQLException e) {
            System.out.println("There was an error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            alertMessage.setText(e.getMessage());
        }
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

    // Loading edit data

    public void loadCustomerInfo(Customer customer) {
        customerID.setText(String.valueOf(customer.getCustomerId()));
        name.setText(customer.getCustomerName());
        phoneNumber.setText(customer.getPhoneNumber());
        addressLine1.setText(customer.getAddressLine1());
        addressLine2.setText(customer.getAddressLine2());
        postalCode.setText(String.valueOf(customer.getPostalCode()));

        // Get City and update

        Integer cityId = customer.getCityId();

        if (cityId == 1){
            citySelect.setValue("New York");
        }
        else if (cityId == 2){
            citySelect.setValue("Los Angeles");
        }
        else if (cityId == 3){
            citySelect.setValue("Toronto");
        }
        else if (cityId == 4){
            citySelect.setValue("Vancouver");
        }
        else if (cityId == 5){
            citySelect.setValue("Oslo");
        }

        // Set Header Label
        headerLabel.setText("Edit Customer");

        // Set to edit
        edit = true;

        // Load address ID
        addressId = customer.getAddressId();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> cities = FXCollections.observableArrayList("New York", "Los Angeles", "Toronto", "Vancouver", "Oslo");
        citySelect.setItems(cities);

    }
}
