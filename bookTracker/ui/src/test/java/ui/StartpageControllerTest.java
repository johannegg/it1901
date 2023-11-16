package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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

/**
 * Class for testing StartpageController and its fxml-file.
 */
public class StartpageControllerTest extends ApplicationTest {

  private StartpageController controller;
  private DirectDataAccess directDataAccess;

  /**
   * Set up for testing StartpageController.java
   */
  @Override
  public void start(final Stage stage) throws Exception {
    directDataAccess = new DirectDataAccess(createTestUserObject());
    StartpageController.setTestDataAccess(true);
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Startpage.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    this.controller.setDataAccess(directDataAccess);
    stage.setScene(new Scene(root));
    stage.show();
    StartpageController.setTestDataAccess(false);
  }

  /**
   * Help method for generating test object.
   *
   * @return users objects for use in testing
   * @throws IOException
   */
  public User createTestUserObject() throws IOException {
    User newUser = new User("test@mail.com", "TestUserStart", "password1");
    newUser.setLoggedIn(true);
    newUser.setBookShelf(new BookShelf());
    return newUser;
  }

  /**
   * Test to check if User BookShelf is not null.
   */
  @Test
  public void testUserBookShelf() {
    assertNotNull(directDataAccess.getLoggedInUser().getBookShelf());
  }

  /**
   * Click on a book and "Add"-button to add Book to BookShelf.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void testAddBook() throws InterruptedException {
    clickOn("#gilmore");
    clickOn("#addBook");
    clickOn("OK");
    Users users = directDataAccess.getUsers();
    User user = users.getUser("TestUserStart");
    BookShelf shelf = user.getBookShelf();
    assertNotNull(shelf.getBook("gilmore"));
  }

  /**
   * Test to search up a book and add Book to BookShelf.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   * @throws IOException
   */
  @Test
  public void testSearchBook() throws InterruptedException, IOException {
    clickOn("#searchBar").write("Trust");
    clickOn("#searchBtn");
    clickOn("Trust - Hernan Diaz");
    clickOn("#addBook");
    clickOn("OK");
    Users users = directDataAccess.getUsers();
    User user = users.getUser("TestUserStart");
    BookShelf shelf = user.getBookShelf();
    assertNotNull(shelf.getBook("diaz"));
  }

  /**
   * Test to check if the UI changes Windiw and Scene when "SHELF"-button is
   * clicked.
   * 
   * @throws InterruptedException if Thread.sleep() fails.
   */
  @Test
  public void testShelfScene() throws InterruptedException {
    List<Window> before = Window.getWindows();
    Parent beforeRoot = null;
    for (Window window : before) {
      beforeRoot = window.getScene().getRoot();
    }
    ShelfController.setTestDataAccess(true);
    clickOn("#shelf_button");
    ShelfController.setTestDataAccess(false);
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