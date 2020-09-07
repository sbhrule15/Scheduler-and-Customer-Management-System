package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    //Current User
    private User currentUser;

    // Passing of UserObject
    void initUser(User user) {
        currentUser = user;
    }

    @FXML
    private Label title;

    @FXML
    private TableView <Report> reportTable;

    ObservableList<Report> reportList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBack(){
        handleSceneChange();
    }

    @FXML
    private void handleSceneChange() {
        Parent main = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
            main = loader.load();
            Scene scene = new Scene(main);

            Stage stage = Main.getStage();
            stage.setScene(scene);

            // Pass User Object to Calendar Controller
            CalendarController controller = loader.getController();
            controller.initUser(currentUser);

            // Show scene
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }



    void monthTypeReport(){

        title.setText("Appointment Type by Month");

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement stmt = connection.prepareStatement("SELECT type, COUNT(type), MONTH(start) FROM appointment GROUP BY MONTH(start), type");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String type = rs.getString("type");
                String count = String.valueOf(rs.getInt("COUNT(type)"));
                String month = String.valueOf(Month.of(rs.getInt("MONTH(start)")));

                Report r = new Report(type,count,month, null);

                reportList.add(r);
            }
        } catch (SQLException e){
            System.out.println("There was an error: " + e);
        }

        TableColumn<Report, String> val1 = new TableColumn("Type");
        TableColumn<Report, String> val2 = new TableColumn("Count");
        TableColumn<Report, String> val3 = new TableColumn("Month");

        reportTable.setItems(reportList);
        reportTable.getColumns().addAll(val1, val2, val3);

        // lambda function to clean up set function
        val1.setCellValueFactory((p ->
                p.getValue().value1Property()
        ));
        val2.setCellValueFactory((p ->
                p.getValue().value2Property()
        ));
        val3.setCellValueFactory((p ->
                p.getValue().value3Property()
        ));

    }

    void consultantScheduleReport(){

        title.setText("Appointments");

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement stmt = connection.prepareStatement("SELECT appointment.title, appointment.type, customer.customerName, appointment.start FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId AND appointment.userId=?");
            stmt.setInt(1, currentUser.getUserID());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String t = rs.getString("title");
                String c = rs.getString("type");
                String m = rs.getString("customerName");
                String ti = rs.getTimestamp("start").toString();

                Report r = new Report(t,c,m,ti);

                reportList.add(r);
            }
        } catch (SQLException e){
            System.out.println("There was an error: " + e);
        }

        TableColumn<Report, String> col1 = new TableColumn("Title");
        TableColumn<Report, String> col2 = new TableColumn("Type");
        TableColumn<Report, String> col3 = new TableColumn("Customer");
        TableColumn<Report, String> col4 = new TableColumn("Time");

        reportTable.setItems(reportList);
        reportTable.getColumns().addAll(col1, col2, col3, col4);

        // lambda function to clean up set function
        col1.setCellValueFactory((p ->
                p.getValue().value1Property()
        ));
        col2.setCellValueFactory((p ->
                p.getValue().value2Property()
        ));
        col3.setCellValueFactory((p ->
                p.getValue().value3Property()
        ));
        col4.setCellValueFactory((p ->
                p.getValue().value4Property()
        ));
    }

    void customerReport(){

        title.setText("Current Customer Record");

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement stmt = connection.prepareStatement("SELECT customer.customerName, concat(address.address , ' ', address.address2) AS address, city.city, customer.lastUpdate FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON city.cityId = address.cityId;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String val1 = rs.getString("customerName");
                String val2 = rs.getString("address");
                String val3 = rs.getString("city");
                String val4 = rs.getTimestamp("lastUpdate").toString();

                Report r = new Report(val1,val2,val3,val4);

                reportList.add(r);
            }
        } catch (SQLException e){
            System.out.println("There was an error: " + e);
        }

        TableColumn<Report, String> col1 = new TableColumn("Name");
        TableColumn<Report, String> col2 = new TableColumn("Address");
        TableColumn<Report, String> col3 = new TableColumn("City");
        TableColumn<Report, String> col4 = new TableColumn("Last Updated");

        reportTable.setItems(reportList);
        reportTable.getColumns().addAll(col1, col2, col3, col4);

        // lambda function to clean up set function
        col1.setCellValueFactory((p ->
                p.getValue().value1Property()
        ));
        col2.setCellValueFactory((p ->
                p.getValue().value2Property()
        ));
        col3.setCellValueFactory((p ->
                p.getValue().value3Property()
        ));
        col4.setCellValueFactory((p ->
                p.getValue().value4Property()
        ));
    }

}
