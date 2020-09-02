package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    private Customer model;

    // LINKED NODES
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



    // METHODS
    @FXML
    void handleEditCustomer(ActionEvent event) {
        // grab customerID from selected customer
        // query customerID information in database
        // create customer object from query
        // pass customer object to scene change
        handleSceneChange("Customer.fxml");
    }
    @FXML
    void handleDeleteCustomer(ActionEvent event) {
        // grab customerID from selected customer
        // delete query using customerID
        // display success message if success, otherwise display error
    }
    @FXML
    void handleAddCustomer(ActionEvent event) {
        handleSceneChange("Customer.fxml");
    }

    @FXML
    void handleCalendarChange(ActionEvent event){
        handleSceneChange("Calendar.fxml");
    }

    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitChange.getScene().getWindow();
        stage.close();
    }

    // SCENE CHANGE

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

        List<Customer> custViewList = new ArrayList<Customer>();

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
                    Customer c = new Customer(custid, custName, custaddiD, custcreated, custcreatedby, null, custphone, custadd, custadd2, custcityid, custpostal);

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
        ObservableList<Customer> observableCustView = FXCollections.observableArrayList(custViewList);

        customerList.setItems(observableCustView);

        // lambda function to clean up set function
        name.setCellValueFactory((p ->
                p.getValue().nameProperty()
        ));
    }

}

