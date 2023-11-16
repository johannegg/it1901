package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Class for testing RegisterController and its fxml-file.
 */
public class RegisterControllerTest extends ApplicationTest {

  private RegisterController controller;
  private DirectDataAccess directDataAccess = new DirectDataAccess();
  private String successfullRegister = "Successfull registration";
  private String unsuccessfullRegister = "Unsuccessfull registration";

  /**
   * Set up for testing RegisterController.java and its fxml-file.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    this.directDataAccess = new DirectDataAccess();
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationPage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    this.controller.setDataAccess(directDataAccess);
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Test to check if the label shows correct label when unsuccessfull register of
   * an User.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void checkUnsuccsessfullRegister() throws InterruptedException {
    Thread.sleep(1000);
    clickOn("#emailField").write("test1@mail.com");
    clickOn("#usernameField").write("Usertwo");
    clickOn("#passwordField").write("pass");
    clickOn("#registerButton");
    Thread.sleep(1000);
    assertEquals(unsuccessfullRegister, controller.getFeedbackText());
  }

  /**
   * Test to check if the label shows correct label when successfull register of
   * an User.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void checkSuccessfullRegister() throws InterruptedException {
    Thread.sleep(1000);
    clickOn("#emailField").write("test1@mail.com");
    clickOn("#usernameField").write("Usertwo");
    clickOn("#passwordField").write("password2");
    clickOn("#registerButton");
    Thread.sleep(1000);
    assertEquals(successfullRegister, controller.getFeedbackText());
  }

  /**
   * Test to check if the UI changes Window and Scene when the "Register"-button
   * is clicked.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void testRegisterButton() throws InterruptedException {
    clickOn("#emailField").write("test1@mail.com");
    clickOn("#usernameField").write("Userthree");
    clickOn("#passwordField").write("password2");
    clickOn("#registerButton");
    Thread.sleep(1000);
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("OK");

    try {
      Thread.sleep(500);
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
   * Method to delete all User objects in the Users object in test_users.json
   * 
   * @throws IOException
   */
  @AfterEach
  public void deleteUsers() throws IOException {
    this.directDataAccess.deleteAllUsers();
  }

}