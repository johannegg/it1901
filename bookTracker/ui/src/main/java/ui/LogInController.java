package ui;

import java.io.IOException;

import core.User;
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

/**
 * Controller connectet to LogInPage.fxml
 */
public class LogInController {

    private UsersPersistence usersPersistence;
    // private RemoteDataAccess dataAccess;

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
        // this.dataAccess = new RemoteDataAccess();
    }

    /**
     * Changes scene to the registration page
     * 
     * @param event click on registerButton
     * @throws IOException if it cannot find the fxml file
     */
    public void handleRegisterButton(ActionEvent event) throws IOException {
        changeScene("RegistrationPage.fxml", event);
    }

    /**
     * Checks if the user exists and changes the scene to StartPage.fxml if it does
     * 
     * @param event click on logInButton
     * @throws IOException if it cannot find the fxml file
     */
    public void handleLogInButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Users users = usersPersistence.readFromUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    changeScene("Startpage.fxml", event);
                    return;
                }
            }
        }
        feedbackLabel.setText("Wrong username or password");

        // try {
        // this.user = this.logIn(usernameField.getText(), passwordField.getText());
        // feedbackLabel.setText("Successfull log in");
        // changeScene("Startpage.fxml", event);
        // } catch (IllegalArgumentException e) {
        // feedbackLabel.setText(e.getMessage());
        // }

    }

    // private User logIn(String username, String passwordInput) throws
    // IllegalArgumentException {
    // User user = dataAccess.getUserByUsername(username);
    // String password = user.getPassword();
    // if (!passwordInput.equals(password)) {
    // throw new IllegalArgumentException("Feil passord");
    // }
    // return dataAccess.getUserByUsername(username);
    // }

    /**
     * Changes the scene
     * 
     * @param filePath the scene to change to
     * @param event    the click
     * @throws IOException if it cannot find the fxml file
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
