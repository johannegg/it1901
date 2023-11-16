package ui;

import java.io.IOException;

import core.BookShelf;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller connected to RegistrationPage.fxml
 */
public class RegisterController extends DataAccessController{

    private String feedbackLabel;

    @FXML
    TextField emailField;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    // @FXML
    // Label feedbackLabel;

    @FXML
    Button registerButton;

    /**
     * Creates a new user if createNewUser() does not throw an
     * IllegalArgumentException. If the registration is successfull
     * an allert appears.
     * 
     * @param event click on registerButton
     * @throws IOException if fxml file cannot be found
     */
    @FXML
    public void handleRegisterButton(ActionEvent event) throws IOException {
        // feedbackLabel.setText("");
        try {
            createNewUser();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successfull registration");
            this.feedbackLabel = "Successfull registration";
            alert.setHeaderText("The registration was Successfull");
            alert.setContentText(
                    "The registration of a new user was successfull. Log in to get the full Book Tracker experience");
            alert.showAndWait();

            changeScene(event);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Unsuccessfull registration");
            this.feedbackLabel = "Unsuccessfull registration";
            alert.setHeaderText("The registration was unsuccessfull");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        changeScene(event);
    }

    /**
     * Creates a new User by using info from the email, username and password
     * fields.
     * 
     * @throws IOException
     */
    private void createNewUser() throws IOException {
        User user = new User();
        user.setEmail(emailField.getText());
        user.setUsername(usernameField.getText());
        user.setPassword(passwordField.getText());
        user.setBookShelf(new BookShelf());
        Users users = this.getDataAccess().getUsers();
        users.checkUsername(user.getUsername());
        this.getDataAccess().postUser(user);
        createUserForTest(user);
    }

    /**
     * Changes the scene to LogIn after the registration of a new user is
     * successfull
     * 
     * @param event click on registerButton
     * @throws IOException if fxml fiel cannot be found
     */
    private void changeScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("LogInPage.fxml"));
        Parent registerUserParent = fxmlLoader.load();
        Scene registerUserScene = new Scene(registerUserParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerUserScene);
        window.show();
    }

    /**
     * Getter for the feedback text when register of new user for UI test.
     *
     * @return text feedback from label
     */
    public String getFeedbackText() {
        return this.feedbackLabel;
    }

    public void createUserForTest(User user) throws IOException {
        DirectDataAccess directDataAccess = new DirectDataAccess();
        directDataAccess.postUser(user);
    }



}
