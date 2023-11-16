package restserver;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;

/**
 * Spring Boot Controller. Can retrieve, add, update and delete users, access
 * library and retrieve a book by ID.
 */

@RestController
@RequestMapping("/api")
public class BookTrackerController {

  @Autowired
  private UsersService usersService;

  @Autowired
  private LibraryService libraryService;

  /**
   * Retrieves a list of users.
   * 
   * @return a User object.
   * @throws IOException if the reading of user data fails.
   */
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public Users getUsers() throws IOException {
    return usersService.getUsers();
  }

  /**
   * Gets a user by its username.
   * 
   * @param username the users username.
   * @return ResponseEntity with User if found, or else a not forund response.
   * @throws IOException if the reading of user data fails.
   */
  @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
  public ResponseEntity<User> getUser(@PathVariable("username") String username) throws IOException {
    User user = getUsers().getUser(username);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Posts a new user to the application.
   * 
   * @param username the users username.
   * @param user the new User object.
   * @return ResponseEntity indicating a successful user post, or an error.
   * @throws IOException if the writing of user data fails.
   */
  @PostMapping("/users/{username}")
  public ResponseEntity<Object> postUser(@PathVariable("username") String username, @RequestBody User user)
      throws IOException {
    usersService.postUser(user);
    return new ResponseEntity<>("Bruker lagt til", HttpStatus.CREATED);
  }

  /**
   * Puts an excisting user to the application.
   * 
   * @param username the users username.
   * @param user the new User object
   * @return ResponseEntity indicating a successful user put, or an error.
   * @throws IOException if the writing of user data fails.
   */
  @PutMapping("/users/{username}")
  public ResponseEntity<Object> putUser(@PathVariable("username") String username, @RequestBody User user)
      throws IOException {
    usersService.putUser(user);
    return new ResponseEntity<>("Bruker endret", HttpStatus.OK);
  }

  /**
   * Gets the libraray
   * 
   * @return a library as an BookShelf object.
   * @throws IOException if the reading of user data fails.
   */
  @GetMapping("/library")
  public BookShelf getLibrary() throws IOException {
    return libraryService.getLibrary();
  }

  /**
   * Gets a book by its ID from the library
   * 
   * @param bookId the books ID
   * @return the Book object if found, or null if not.
   * @throws IOException if the reading of user data fails.
   */

  @GetMapping("/library/{bookId}")
  public Book getBook(@PathVariable("bookId") String bookId) throws IOException {
    return libraryService.getLibrary().getBook(bookId);
  }

  /**
   * Sets the file name for storing data. Used for testing.
   * 
   * @param fileName the name of the json file storing users data.
   */
  public void setUsersFileName(String fileName) {
    this.usersService.setUsersJsonFileName(fileName);
  }

  /**
   * Deletes all users from the application.
   * 
   * @throws IOException if deleting fails.
   */
  @DeleteMapping("/users/delete")
  public void deleteUsers() throws IOException {
    usersService.deleteUsers();
  }

}