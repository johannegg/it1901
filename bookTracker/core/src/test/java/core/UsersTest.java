package core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersTest {
    private Users users;
    private User user1;
    private User user2;
    private User testUser;

    @BeforeEach
    public void setUp() throws IOException {
        users = new Users();
        user1 = new User("suzanne@ntnu.no", "suzanne123", "jegerkul123");
        user2 = new User("johanne@ntnu.no", "johanne123", "jegerkul123");

        users.addUserForTest(user1);
        users.addUserForTest(user2);
        
    }

    @Test
    public void testAddUser() throws IOException {
        List<User> result = new ArrayList<>();
        result.add(user1);
        result.add(user2);
        assertArrayEquals(result.toArray(), users.getUsers().toArray());
    }

    
    @Test
    public void testFailsWhenAddUser() throws IOException {
        testUser = new User("test@ntnu.no", "test", "test1234");
        Throwable exception =  Assertions.assertThrows(IllegalArgumentException.class, 
        () -> users.addUser(testUser));
        assertEquals("Username already exists", exception.getMessage());
    }


    @Test
    public void testGetUser() throws IOException {
        assertEquals(user1, users.getUser("suzanne@ntnu.no"));
        assertEquals(null, users.getUser("tull@ntnu.no"));
    }

    @Test
    public void testRemoveUser() throws IOException {
        users.removeUser(user2);
        List<User> result = new ArrayList<>();
        result.add(user1);
        assertArrayEquals(result.toArray(), users.getUsers().toArray());
    }
}
