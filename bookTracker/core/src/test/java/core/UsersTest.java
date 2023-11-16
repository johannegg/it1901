package core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing the Users class and its methods.
 */
public class UsersTest {
    private Users users;
    private User user1;
    private User user2;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() {
        users = new Users();
        user1 = new User("suzanne@ntnu.no", "suzanne123", "jegerkul123");
        user2 = new User("johanne@ntnu.no", "johanne123", "jegerkul123");

        users.addUser(user1);
        users.addUser(user2);

    }

    /**
     * Method that tests addUser().
     */
    @Test
    public void testAddUser() {
        User newUser = new User("new@ntnu.no", "newUser", "newPassword123");
        users.addUser(newUser);
        assertEquals(newUser, users.getUser("newUser"));
    }

    /**
     * Method that tests getUser().
     */
    @Test
    public void testGetUser() {
        assertEquals(user1, users.getUser("suzanne123"));
        assertEquals(null, users.getUser("tull"));
    }

    /**
     * Method that tests removeUser().
     */
    @Test
    public void testRemoveUser() {
        users.removeUser(user2);
        List<User> result = new ArrayList<>();
        result.add(user1);
        assertArrayEquals(result.toArray(), users.getUsers().toArray());
    }

    /**
     * Method that tests checkUsername().
     */
    @Test
    public void testCheckUsername() {
        assertThrows(IllegalArgumentException.class, () -> users.checkUsername("suzanne123"));
        assertDoesNotThrow(() -> users.checkUsername("ukjent"));
    }

    /**
     * Method that tests iterator().
     */
    @Test
    public void testIterator() {
        Iterator<User> iterator = users.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(user1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(user2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
