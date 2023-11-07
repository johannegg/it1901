package bookTracker.restserver;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import core.User;
import core.Users;

/**
 * The service implementation.
 */

@RestController
@RequestMapping("/users")
public class BookTrackerController {

  @Autowired
  private UsersService usersService;
  

  @GetMapping
  public Users getUsers() throws IOException {
    return usersService.getUsers();
  }

  @GetMapping("/{username}")
  public User getUser(@PathVariable("username") String username) throws IOException {
    User user = getUsers().getUser(username);
    return user;
  }

  @PostMapping(path = "/{username}")
  public ResponseEntity<Object> postUser(@PathVariable("username") String username, @RequestBody User user) throws IOException {
    usersService.postUser(user);
    return new ResponseEntity<>("Bruker lagt til", HttpStatus.CREATED);
  }

  @PutMapping(path = "/{username}")
  public ResponseEntity<Object> putUser(@PathVariable("username") String username, @RequestBody User user) throws IOException {
    usersService.putUser(user);
    return new ResponseEntity<>("Bruker endret", HttpStatus.OK);
  }

}
