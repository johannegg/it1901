package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.User;
import core.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Class for testing ProfileController and its fxml-file.
 */
public class ProfileControllerTest extends ApplicationTest {

    private ProfileController controller;
    private User user;
    private DirectDataAccess directDataAccess = new DirectDataAccess();

    @Override
    public void start(final Stage stage) throws Exception {
        setUpUser();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        directDataAccess.readUsers();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Help method for generating test object
     *
     * @return users objects for use in testing
     */
    public Users createTestUserObject() throws IOException {
        Users users = new Users();
        User newUser = new User("test@mail.com", "TestUser", "password1");
        users.addUser(newUser);
        return users;
    }

    /**
     * Set up a User to use in the test
     */
    public void setUpUser() {
        this.user = new User();
        this.user.setUsername("TestUser");
        this.user.setPassword("password1");
        this.user.setEmail("test@gmail.com");
    }

    /**
     * Test to check if user information in the fxml-file match the User object
     */
    @Test
    public void checkUser() {
        assertEquals("TestUser", this.controller.getLabelName());
        assertEquals("test@gmail.com", this.controller.getLabelEmail());
    }

    /**
     * Test to check if the UI shows a pop up scene when the "Submit"-button is
     * clicked.
     */
    @Test
    public void testSubmitNewPasswordButton() {
        List<Window> before = Window.getWindows();
        Parent beforeRoot = null;
        for (Window window : before) {
            beforeRoot = window.getScene().getRoot();
        }
        clickOn("#passwordButton");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            fail();
        }
        List<Window> after = Window.getWindows();
        Parent afterRoot = null;
        for (Window window : after) {
            afterRoot = window.getScene().getRoot();
        }
        assertNotEquals(afterRoot, beforeRoot);
    }

}
