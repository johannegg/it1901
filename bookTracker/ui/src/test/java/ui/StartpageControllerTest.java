package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.BookShelf;
import core.User;
import core.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StartpageControllerTest extends ApplicationTest {

  private StartpageController controller;
  private DirectDataAccess directDataAccess = new DirectDataAccess();

  /**
   * Set up for testing StartpageController.java
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Startpage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    directDataAccess.readUsers();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Test to check if pop up shows up when a book is clicked.
   */
  @Test
  public void testBookClicked() {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    clickOn("#gilmore");
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
   * Click "Add"-button to add Book to BookShelf
   */
  @Test
  public void testAddBook() {
    clickOn("addButton");
  }

  /**
   * Test to check if Book is in BookShelf
   */
  @Test
  public void checkIfBookInShelf() {
    Users users = directDataAccess.readUsers();
    User user = users.getUser("TestUser");
    BookShelf shelf = user.getBookShelf();
    assertNotNull(shelf.getBook("gilmore"));
  }

}
