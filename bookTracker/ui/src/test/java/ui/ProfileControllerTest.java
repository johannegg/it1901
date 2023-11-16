package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.User;
import core.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for testing ProfileController and its fxml-file.
 */
public class ProfileControllerTest extends ApplicationTest {

    private ProfileController controller;
    private DirectDataAccess directDataAccess;
    private String oldPassword = "password1";

    /**
     * Set up for testing ProfileController.java.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        directDataAccess = new DirectDataAccess(createTestUserObject());
        ProfileController.setTestDataAccess(true);
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        this.controller.setDataAccess(directDataAccess);
        stage.setScene(new Scene(root));
        stage.show();
        ProfileController.setTestDataAccess(false);
    }

    /**
     * Help method for generating test object.
     *
     * @return users objects for use in testing.
     */
    public User createTestUserObject() throws IOException {
        Users users = new Users();
        User newUser = new User("test@mail.com", "TestUserProfile", "password1");
        users.addUser(newUser);
        return newUser;
    }

    /**
     * Test to check if user information in the fxml-file match the User object.
     */
    @Test
    public void checkUser() {
        assertEquals("TestUserProfile", directDataAccess.getLoggedInUser().getUsername());
        assertEquals("test@mail.com", directDataAccess.getLoggedInUser().getEmail());
    }

    /**
     * Test to check if new password is updated.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     */
    @Test
    public void testSubmitNewPasswordButton() throws InterruptedException {
        clickOn("#passwordField").write("NewPassword123");
        clickOn("#passwordButton");
        clickOn("OK");
        assertNotEquals(oldPassword, directDataAccess.getLoggedInUser().getPassword());
    }

    /**
     * Test to check if User is logged out.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     */
    @Test
    public void checkIfLoggedOut() throws InterruptedException {
        clickOn("#logoutButton");
        Thread.sleep(1000);
        assertNull(directDataAccess.getLoggedInUser());
    }

    /**
     * Method to delete all User objects in the Users object in test_users.json
     * 
     * @throws IOException
     */
    @AfterEach
    public void deleteUsers() throws IOException {
        directDataAccess.deleteAllUsers();
    }
}