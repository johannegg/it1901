package bookTracker.restserver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  // @GetMapping("/hello")
  // public User hello(){
  //   return new User("elias@gmail.com", "elias2", "12345gasuciaa");
  // }
  
  @GetMapping
  public Users getUsers() throws IOException {
    return usersService.getUsers();
  }

  @GetMapping(path = "/{username}")
  public User getUser(@PathVariable("username") String username) throws IOException {
    User user = getUsers().getUser(username);
    return user;
  }

  @PutMapping("/{username}")
  public void putUser(@PathVariable("username") String username, @RequestBody User user) throws IOException {
    usersService.putUser(user);
  }
  
}
