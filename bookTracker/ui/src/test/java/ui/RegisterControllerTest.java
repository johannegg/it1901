package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterControllerTest extends ApplicationTest {

  private RegisterController controller;
  private DirectDataAccess directDataAccess = new DirectDataAccess();
  private String successfullRegister = "Successfull registration";
  private String unsuccessfullRegister = "Unsuccessfull registration";

  /**
   * Set up for testing RegisterController.java and its fxml-file
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationPage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    directDataAccess.readUsers();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupUsers() throws InterruptedException {
    Thread.sleep(500);
    clickOn("#emailField").write("test1@mail.com");
    clickOn("#usernameField").write("Usertwo");
    clickOn("#passwordField").write("password2");
  }

  /**
   * Test to check if the label shows correct label when successfull register of
   * an User.
   */
  @Test
  public void checkSuccessfullRegister() {
    clickOn("#registerButton");
    assertEquals(successfullRegister, controller.getFeedbackText());
  }

  /**
   * Test to check if the label shows correct label when unsuccessfull register of
   * an User.
   */
  @Test
  public void checkUnsuccsessfullRegister() {
    clickOn("#passwordField").write("pass");
    clickOn("#registerButton");
    assertEquals(controller.getFeedbackText(), unsuccessfullRegister);
  }

  /**
   * Click "Register"-button
   */
  @Test
  public void checkRegister() {
    clickOn("#registerButton");
  }

  /**
   * Test to check if the UI changes Window and Scene when the "Register"-button
   * is clicked.
   */
  @Test
  public void testRegisterButton() {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#registerButton");
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
