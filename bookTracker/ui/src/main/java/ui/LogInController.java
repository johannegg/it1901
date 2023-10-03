package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller for LogIn.fxml.
 */
public class LogInController {

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    Button LogInButton;

    @FXML
    Button RegisterButton;

    public void handleRegisterButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RegistrationPage.fxml"));
        Parent registerUserParent = fxmlLoader.load();
        Scene registerUserScene = new Scene(registerUserParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerUserScene);
        window.show();
    }

    public void handleLogInButton(ActionEvent event) throws IOException {
        
    }
}
