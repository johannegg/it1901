package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
    this.controller.setDataAccess(directDataAccess);
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Test to check if the UI changes window when the "Log in"-button is clicked.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testLogInButton() throws InterruptedException {
    clickOn("#usernameField").write("TestUser");
    clickOn("#passwordField").write("password1");
    Thread.sleep(2000);
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    StartpageControllerTest.setTestLogIn(true);
    clickOn("#logInButton");
    StartpageControllerTest.setTestLogIn(false);
    try {
      Thread.sleep(3000);
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
   * @throws InterruptedException
   */
  @Test
  public void testRegisterButton() throws InterruptedException {
    Thread.sleep(2000);
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#RegisterButton");
    try {
      Thread.sleep(3000);
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