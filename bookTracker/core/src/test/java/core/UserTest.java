package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing the User class and its methods.
 */
public class UserTest {
    private User user1;
    private User user2;
    private User user3;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() {
        user1 = new User("suzanne@ntnu.no", "suzanne123", "jegerkul123");
        user2 = new User("johanne@ntnu.no", "johanne0504", "jegerbest03");
        user3 = new User();
    }

    /**
     * Method that tests getEmail().
     */
    @Test
    public void testGetEmail() {
        assertEquals("suzanne@ntnu.no", user1.getEmail());
        assertEquals("johanne@ntnu.no", user2.getEmail());
    }

    /**
     * Method that tests getUserName().
     */
    @Test
    public void testGetUsername() {
        assertEquals("suzanne123", user1.getUsername());
        assertEquals("johanne0504", user2.getUsername());
    }

    /**
     * Method that tests getPassword().
     */
    @Test
    public void testGetPassword() {
        assertEquals("jegerkul123", user1.getPassword());
        assertEquals("jegerbest03", user2.getPassword());
    }

    /**
     * Method that tests setUsername().
     */
    @Test
    public void testSetUsername() {
        user3.setUsername("anniken12");
        assertEquals("anniken12", user3.getUsername());
    }

    /**
     * Method that tests setEmail().
     */
    @Test
    public void testSetEmail() {
        user3.setEmail("anniken@ntnu.no");
        assertEquals("anniken@ntnu.no", user3.getEmail());
    }

    /**
     * Method that tests setBookShelf() and getBookShelf().
     */
    @Test
    public void testSetAndGetBookShelf() {
        User user = new User();
        BookShelf bookShelf = new BookShelf();
        user.setBookShelf(bookShelf);
        assertEquals(bookShelf, user.getBookShelf());
    }

    /**
     * Method that tests setLoggedIn() and isLoggedIn().
     */
    @Test
    public void testSetAndIsLoggedIn() {
        User user = new User();
        assertFalse(user.isLoggedIn());

        user.setLoggedIn(true);
        assertTrue(user.isLoggedIn());

        user.setLoggedIn(false);
        assertFalse(user.isLoggedIn());
    }

    /**
     * Method that tests if setPasswors() throws illegalArgumentException when
     * thepassword is invalid.
     */
    @Test
    public void testFailsWhenSetPassword() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla@ntnu.no", "camilla123", "passordd"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla@ntnu.no", "camilla123", "pass123"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla@ntnu.no", "camilla123", "12345678"));
    }

    /**
     * Method that tests if setUsername() throws IllegalArgumentException when
     * username is empty.
     */
    @Test
    public void testFailsWhenSetUsername() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla@ntnu.no", "", "passord1234"));
    }

    /**
     * Method that tests if setEmail throws illegalArgumentException when it is
     * invalid.
     */
    @Test
    public void testFailsWhenSetEmail() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla", "camilla123", "passord1234"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camilla@ntnuno", "camilla123", "passord1234"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("camillantnu.no", "camilla123", "passord1234"));
    }

}
