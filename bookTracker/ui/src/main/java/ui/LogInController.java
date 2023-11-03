package ui;

import java.io.IOException;

import core.User;
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


/**
 * Controller connectet to LogInPage.fxml
 */
public class LogInController {

    private RemoteDataAccess dataAccess = new RemoteDataAccess();
    private User user;

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
        try {
            this.user = this.logIn(usernameField.getText(), passwordField.getText());
            feedbackLabel.setText("Successfull log in");
            changeScene("Startpage.fxml", event);
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
        }

    }

    private User logIn(String username, String passwordInput){
        User user = dataAccess.getUserByUsername(username);
        String password = user.getPassword();
        if (!passwordInput.equals(password)) {
            throw new IllegalArgumentException("Wrong username or password");
        }
        return dataAccess.getUserByUsername(username);
    }

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
