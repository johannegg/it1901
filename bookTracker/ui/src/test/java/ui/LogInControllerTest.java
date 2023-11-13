package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
 * Class for testing LogInController and its fxml-file.
 */
public class LogInControllerTest extends ApplicationTest {

  private LogInController controller;
  private DirectDataAccess directDataAccess = new DirectDataAccess();

  /**
   * Set up for testing LogInController.java
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    directDataAccess.readUsers();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Help method for creating test object
   *
   * @return users objects for use in testing
   */
  public Users createTestUserObject() throws IOException {
    Users users = new Users();
    User user = new User("test@mail.com", "TestUser", "password1");
    users.addUser(user);
    return users;
  }

  /**
   * Writes existing user to textfields for successfull log in.
   *
   * @throws InterruptedException if Thread.sleep() fails
   */
  @BeforeEach
  public void setupUsers() throws InterruptedException {
    Thread.sleep(1000);
    clickOn("#usernameField").write("TestUser");
    clickOn("#passwordField").write("password1");
    Thread.sleep(500);
  }

  /**
   * Test to check if the UI changes window when the "Log in"-button is clicked.
   */
  @Test
  public void testLogInButton() {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#logInButton");
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

  /**
   * Test to check if the UI changes Window when the "Register"-button is clicked.
   */
  @Test
  public void testRegisterButton() {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#RegisterButton");
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