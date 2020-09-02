package sample;

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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private TableView customerList;
    @FXML
    private TableColumn name;



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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM customer");

            ResultSet results = statement.getResultSet();
            while(results.next()) {
                System.out.println( results.getString("customerName") + " : " +
                                    results.getString("customerId") + " : " +
                                    results.getString("addressId"));
            }

            results.close();

            statement.close();
            connection.close();

        } catch(SQLException e) {
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        }

    }

}
