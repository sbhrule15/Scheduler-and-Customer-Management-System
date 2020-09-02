package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Button submitLogin;
    @FXML
    private Button exitLogin;


    @FXML
    void handleLoginSubmit(javafx.event.ActionEvent event) {
        handleChangeScene();
    }

    @FXML
    void handleExit(javafx.event.ActionEvent event) {
        Stage stage = (Stage) exitLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChangeScene() {
        Parent main = null;
        try {
            main = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
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
    }


}
