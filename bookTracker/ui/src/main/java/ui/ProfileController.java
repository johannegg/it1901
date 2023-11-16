package ui;

import java.io.IOException;

import core.User;
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

/**
 * Controller for the ProfielPage.fxml.
 * Manages the interactions on the profile page.
 */
public class ProfileController extends DataAccessController {

    private User loggedInUser;
    private static boolean TestDataAccess = false;

    @FXML
    private Button profileButton;

    @FXML
    private Button shelfButton;

    @FXML
    private Button homePageButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button passwordButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label usernameTag;

    @FXML
    private Label showUsername;

    @FXML
    private Label showEmail;

    /**
     * Sets up the profile page by displaying the user's username and email.
     * User can also change password.
     */
    public void initialize() throws IOException {
        if (TestDataAccess == true) {
            User newUser = new User("test@mail.com", "TestUser111", "password1");
            this.setDataAccess(new DirectDataAccess(newUser));
        }
        this.loggedInUser = this.getDataAccess().getLoggedInUser();
        usernameTag.setText(loggedInUser.getUsername());
        showUsername.setText("Username: " + loggedInUser.getUsername());
        showEmail.setText("E-mail: " + loggedInUser.getEmail());
    }

    /**
     * Sets the test data access mode, and controlls whether the application uses
     * test data.
     * 
     * @param bool true to enable test data access, false otherwise.
     */
    public static void setTestDataAccess(boolean bool) {
        TestDataAccess = bool;
    }

    /**
     * Handles the action when profile button is clicked on by switching to the
     * profile page.
     * 
     * @param event ActionEvent triggered by the profile button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     profile page.
     */
    public void handleProfileButton(ActionEvent event) throws IOException {
        changeScene("/ui/ProfilePage.fxml", event);
    }

    /**
     * Handles the action when shelf button is clicked on by switching to the shelf
     * page.
     * 
     * @param event ActionEvent triggered by the shelf button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     shelf page.
     */
    public void handleShelfButton(ActionEvent event) throws IOException {
        changeScene("/ui/ShelfPage.fxml", event);
    }

    /**
     * Handles the action when homepage button is clicked on by switching to the
     * homepage.
     * 
     * @param event ActionEvent triggered by the homepage button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     homepage.
     */
    public void handleHomePageButton(ActionEvent event) throws IOException {
        changeScene("/ui/Startpage.fxml", event);
    }

    /**
     * Handles the action when user wants to change password.
     * 
     * @throws IOException if an I/O error occurs during the during the process
     *                     of changig password.
     * 
     */
    public void handleChangePassword() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Change password");
        alert.setHeaderText("Are you sure you want to change your password?");
        alert.showAndWait();

        loggedInUser.setPassword(passwordField.getText());
        this.getDataAccess().putUser(loggedInUser);
    }

    /**
     * Handles the action when logout button is clicked on by switching to the login
     * page.
     * 
     * @param event ActionEvent triggered by the logout button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     login page.
     */
    public void handleLogoutButton(ActionEvent event) throws IOException {
        loggedInUser.setLoggedIn(false);
        this.getDataAccess().putUser(loggedInUser);
        changeScene("/ui/LogInPage.fxml", event);
    }

    /**
     * Changes the scene to the given file path.
     * 
     * @param filePath the fxml file to change to.
     * @param event    the mouse click.
     * @throws IOException if the file cannot be found.
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

    /**
     * Getter for test-methods.
     *
     * @return the username currently showing in the showUsername label.
     */
    public String getLabelName() {
        return showUsername.getText();
    }

    /**
     * Getter for test method.
     *
     * @return the email currently showing in the showEmail label.
     */
    public String getLabelEmail() {
        return showEmail.getText();
    }
}
