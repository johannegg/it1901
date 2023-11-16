package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.Users;
import core.User;
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
   * Set up for testing LogInController.java.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    DataAccess directDataAccess = new DirectDataAccess(this.createTestUserObject());
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    this.controller.setDataAccess(directDataAccess);
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Help method for generating test object.
   *
   * @return users objects for use in testing.
   */
  public User createTestUserObject() throws IOException {
    Users users = new Users();
    User newUser = new User("test@mail.com", "TestUser", "password1");
    users.addUser(newUser);
    return newUser;
  }

  /**
   * Test to check if the UI changes window when the "Log in"-button is clicked.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void testLogInButton() throws InterruptedException {
    clickOn("#usernameField").write("TestUser");
    clickOn("#passwordField").write("password1");
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    StartpageController.setTestDataAccess(true);
    clickOn("#logInButton");
    StartpageController.setTestDataAccess(false);
    try {
      Thread.sleep(1000);
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
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void testRegisterButton() throws InterruptedException {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#RegisterButton");
    try {
      Thread.sleep(1000);
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
   * Test to check if log in is unsuccessfull.
   *
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void checkUnsuccsessfullLogIn() throws InterruptedException {
    clickOn("#usernameField").write("WrongUsername");
    clickOn("#passwordField").write("WrongPassword");
    clickOn("#logInButton");
    Thread.sleep(1000);
    assertEquals("Wrong username or password", this.controller.getWrongPassword());
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