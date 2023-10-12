package ui;

import java.io.IOException;
import java.util.List;

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

/**
 * Controller connectet to LogInPage.fxml
 */
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

    /**
     * Changes scene to the registration page
     * 
     * @param event         click on registerButton
     * @throws IOException  if it cannot find the fxml file
     */
    public void handleRegisterButton(ActionEvent event) throws IOException {
        changeScene("RegistrationPage.fxml", event);
    }

    /**
     * Checks if the user exists and changes the scene to StartPage.fxml if it does
     * @param event         click on logInButton
     * @throws IOException  if it cannot find the fxml file 
     */
    public void handleLogInButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        List<String> usernames = usersPersistence.readFromUsers();
        boolean usernameExists = false;
        for (String u : usernames) {
            if (username.equals(u)) {
                usernameExists = true;
                filePath = "../core/src/main/java/resources/" + u + ".json";
                // feedbackLabel.setText("Username is found");
            }

        }
        if (usernameExists) {
            String password = usersPersistence.readFromUser(filePath);
            if (password.equals(passwordField.getText())) {
                changeScene("Startpage.fxml", event);
            } else {
                feedbackLabel.setText("Wrong username or password");
            }
        } else {
            feedbackLabel.setText("Wrong username or password");
        }
    }

    /**
     * Changes the scene
     * @param filePath      the scene to change to
     * @param event         the click
     * @throws IOException  if it cannot find the fxml file
     */
    private void changeScene(String filePath, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(filePath));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
