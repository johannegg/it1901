package ui;

import java.io.IOException;

import org.testfx.framework.junit5.ApplicationTest;

import core.User;
import core.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileControllerTest extends ApplicationTest {

    private ProfileController controller;
    private User user;

    @Override
    public void start(final Stage stage) throws Exception {
        setUpUser();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile_test.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        // this.controller.setDataAccess(new
        // DirectDataAccess(this.generateTestUsersObject()));
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
        User user = new User("test@mail.com", "TestUser", "password1");
        users.addUser(user);
        return users;
    }

    private void setUpUser() {
        this.user = new User();
        this.user.setUsername("TestUser");
        this.user.setPassword("password1");
        this.user.setEmail("test@gmail.com");
        //
    }
}
