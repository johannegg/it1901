package ui;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Class for testing ShelfController and its fxml-file.
 */
public class ShelfControllerTest extends ApplicationTest {

    private ShelfController controller;
    private DirectDataAccess directDataAccess;

    /**
     * Set up for testing ShelfController.java and its fxml-file.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        directDataAccess = new DirectDataAccess(createTestUserObject());
        ShelfController.setTestDataAccess(true);
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ShelfPage.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        this.controller.setDataAccess(directDataAccess);
        stage.setScene(new Scene(root));
        stage.show();
        ShelfController.setTestDataAccess(false);
    }

    /**
     * Help method for generating test object.
     *
     * @return users objects for use in testing
     */
    public User createTestUserObject() throws IOException {
        Users users = new Users();
        User newUser = new User("test@mail.com", "TestUser", "password1");
        newUser.setLoggedIn(true);
        BookShelf shelf = new BookShelf();
        Book book = new Book();
        book.setTitle("The Pumpkin Spice Café");
        book.setAuthor("Laurie Gilmore");
        book.setPages("384");
        book.setBookId("gilmore");
        book.setDescription("A heartwarming tale set in a cozy cafe with a touch of pumpkin spice.");
        shelf.addBook(book);
        newUser.setBookShelf(shelf);
        users.addUser(newUser);
        return newUser;
    }

    /**
     * Click on a book and "Remove"-button to remove Book from BookShelf.
     * 
     * @throws InterruptedException if Thread.sleep() fails.
     */
    @Test
    public void testAddBook() throws InterruptedException {
        clickOn("#gilmore");
        clickOn("#removeBook");
        clickOn("#doneBtn");
        Thread.sleep(500);
    }

    /**
     * Test to check if the UI changes Windiw and Scene when "SHELF"-button is
     * clicked.
     * 
     * @throws InterruptedException if Thread.sleep() fails.
     */
    @Test
    public void testStartPageScene() throws InterruptedException {
        List<Window> before = Window.getWindows();
        Parent beforeRoot = null;
        for (Window window : before) {
            beforeRoot = window.getScene().getRoot();
        }
        StartpageController.setTestDataAccess(true);
        clickOn("#homePageButton");
        StartpageController.setTestDataAccess(false);
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
        directDataAccess.deleteAllUsers();
    }

}
