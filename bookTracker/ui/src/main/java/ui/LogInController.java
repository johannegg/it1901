package ui;

import java.io.IOException;
import java.util.List;

import core.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import json.UsersPersistence;

public class LogInController {

    private UsersPersistence usersPersistence;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    Button LogInButton;

    @FXML
    Button RegisterButton;

    @FXML
    Label feedbackLabel;

    String filePath;

    @FXML
    void initialize() {
        usersPersistence = new UsersPersistence();
    }

    public void handleRegisterButton(ActionEvent event) throws IOException {
        changeScene("RegistrationPage.fxml", event);
    }

    public void handleLogInButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        List<String> usernames = usersPersistence.readFromUsers();
        boolean usernameExists = false;
        for (String u : usernames) {
            if (username.equals(u)) {
                usernameExists = true;
                filePath = "../core/src/main/java/resources/" + u + ".json";
                feedbackLabel.setText("Username is found");
            }

        }
        if (usernameExists){
            
        }
    }

    private void changeScene(String filePath, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(filePath));
        Parent registerUserParent = fxmlLoader.load();
        Scene registerUserScene = new Scene(registerUserParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerUserScene);
        window.show();
    }
}
