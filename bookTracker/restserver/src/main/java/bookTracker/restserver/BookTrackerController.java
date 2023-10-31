package bookTracker.restserver;

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
@RequestMapping("/api")
public class BookTrackerController {


  @GetMapping("/hello")
  public User hello(){
    return new User("elias@gmail.com", "elias2", "12345gasuciaa");
  }
  
}
