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

public class LogInControllerTest extends ApplicationTest {

  private LogInController controller;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  public Users createTestUserObject() throws IOException {
    Users users = new Users();
    User user1 = new User("test@mail.com", "TestUser", "password1");
    users.addUser(user1);
    return users;
  }

  @BeforeEach
  public void setupUsers() throws InterruptedException {
    Thread.sleep(1500);
    clickOn("#usernameField").write("TestUser");
    clickOn("#passwordField").write("password1");
    Thread.sleep(500);
  }

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