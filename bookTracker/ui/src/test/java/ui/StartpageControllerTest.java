package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StartpageControllerTest extends ApplicationTest {

  private StartpageController controller;

  /**
   * Set up for testing StartpageController.java
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Startpage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Test to check if the UI changes Window when the "SHELF"-button is clicked.
   */
  @Test
  public void testShelfButton() {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#shelf_button");
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
