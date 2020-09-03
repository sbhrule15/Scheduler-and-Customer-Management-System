package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Button submitLogin;
    @FXML
    private Button exitLogin;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label errorMessage;


    @FXML
    void handleLoginSubmit(ActionEvent event) {

        // get credentials
        String usernameEntry = username.getText();
        String passwordEntry = password.getText();

        // connect to database and set up statement
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com:3306/U07Vgt", "U07Vgt", "53689140721");
            PreparedStatement loginstmt = connection.prepareStatement("SELECT * FROM user WHERE userName = ? AND password = ?");
            loginstmt.setString(1, usernameEntry);
            loginstmt.setString(2, passwordEntry);

            // Query and get results
            ResultSet rs = loginstmt.executeQuery();

            // Check for valid login
            if (rs == null){
                errorMessage.setText("Invalid Credentials. Please try again.");
            } else {
                // grab data and load into new User object. This will be passed between scenes
                while (rs.next()) {
                    String usernameReturned = rs.getString("userName");
                    Integer userIdReturned = rs.getInt("userId");

                    User user = new User(userIdReturned, usernameReturned);

                    // Scene change with new user object
                    handleChangeScene(user);
                }
            }

        } catch (SQLException e){
            System.out.println("There was an error connecting to the database: " + e.getMessage());
        }
    }

    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChangeScene(User currentUser) {

        Parent main = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
            main = loader.load();
            Scene scene = new Scene(main);

            Stage stage = Main.getStage();
            stage.setScene(scene);

            // Pass User Object to Controller
            CalendarController controller = loader.getController();
            controller.initUser(currentUser);

            // Show scene
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
