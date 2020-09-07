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
                String t = rs.getString("type");
                String c = String.valueOf(rs.getInt("COUNT(type)"));
                String m = String.valueOf(Month.of(rs.getInt("MONTH(start)")));

                Report r = new Report(t,c,m);

                reportList.add(r);
            }
        } catch (SQLException e){
            System.out.println("There was an error: " + e);
        }

        TableColumn<Report, String> type = new TableColumn("Type");
        TableColumn<Report, String> count = new TableColumn("Count");
        TableColumn<Report, String> month = new TableColumn("Month");

        reportTable.setItems(reportList);
        reportTable.getColumns().addAll(type, count, month);

        // lambda function to clean up set function
        type.setCellValueFactory((p ->
                p.getValue().typeProperty()
        ));
        count.setCellValueFactory((p ->
                p.getValue().countProperty()
        ));
        month.setCellValueFactory((p ->
                p.getValue().monthProperty()
        ));

    }

}
