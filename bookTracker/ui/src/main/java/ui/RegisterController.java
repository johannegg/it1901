package ui;

import java.io.IOException;

import core.User;
import core.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    TextField emailField;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    Label feedbackLabel;

    @FXML
    Button RegisterButton;

    @FXML
    public void handleRegisterButton(ActionEvent event) throws IOException {
        feedbackLabel.setText("");
        try {
            createNewUser();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successfull registration");
            alert.setHeaderText("The registration was Successfull");
            alert.setContentText(
                    "The registration of a new user was successfull. Log in to get the full Book Tracker experience");
            alert.showAndWait();

            changeScene(event);
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
        }
    }

    private User createNewUser() throws IllegalArgumentException, IOException {
        User user = new User();
        user.setEmail(emailField.getText());
        user.setUsername(usernameField.getText());
        user.setPassword(passwordField.getText());

        Users users = new Users();
        users.addUser(user);
        return user;
    }

    private void changeScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("LogInPage.fxml"));
        Parent registerUserParent = fxmlLoader.load();
        Scene registerUserScene = new Scene(registerUserParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerUserScene);
        window.show();
    }

}
