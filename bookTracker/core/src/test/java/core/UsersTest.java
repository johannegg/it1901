package core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing Users
 */
public class UsersTest {
    private Users users;
    private User user1;
    private User user2;

    /**
     * Method for setting up the test correctly before running each test.
     */
    @BeforeEach
    public void setUp() throws IOException {
        users = new Users();
        user1 = new User("suzanne@ntnu.no", "suzanne123", "jegerkul123");
        user2 = new User("johanne@ntnu.no", "johanne123", "jegerkul123");

        users.addUserForTest(user1); // A method created for testing so that it does not actually create new users
        users.addUserForTest(user2);

    }

    /**
     * Tests addUser()
     */

    @Test
    public void testAddUser() throws IOException {
        User newUser = new User("new@ntnu.no", "newUser", "newPassword123");
        users.addUser(newUser);
        assertEquals(newUser, users.getUser("newUser"));
    }

    /**
     * Tests getUser()
     * 
     * @throws IOException if username already exists
     */
    @Test
    public void testGetUser() {
        assertEquals(user1, users.getUser("suzanne123"));
        assertEquals(null, users.getUser("tull"));
    }

    /**
     * Tests removeUser()
     */
    @Test
    public void testRemoveUser() throws IOException {
        users.removeUser(user2);
        List<User> result = new ArrayList<>();
        result.add(user1);
        assertArrayEquals(result.toArray(), users.getUsers().toArray());
    }

    @Test
    public void testCheckUsername() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> users.checkUsername("suzanne123"));
        assertDoesNotThrow(() -> users.checkUsername("ukjent"));
    }

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
