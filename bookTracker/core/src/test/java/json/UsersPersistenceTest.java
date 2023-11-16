package json;

import core.User;
import core.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for testing the UsersPersistence class and its methods.
 */
public class UsersPersistenceTest {

    private UsersPersistence usersPersistence;
    private File file;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() throws IOException {
        usersPersistence = new UsersPersistence();
        file = new File("../core/src/test/resources/testUsers.json");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Method that deletes the test file if it exists to ensure a clean environment
     * for each test.
     */
    @AfterEach
    public void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Method that tests readFromUsers() and writeToUsers().
     */
    @Test
    public void testWriteToAndReadFromUsers() throws IOException {
        usersPersistence.setFile(new File("../core/src/test/resources/testUsers.json"));
        Users expectedUsers = new Users();
        User user1 = new User();
        user1.setEmail("abc@ntnu.no");
        user1.setUsername("abc");
        user1.setPassword("abcabc123");
        expectedUsers.addUser(user1);
        usersPersistence.writeToUsers(expectedUsers);
        Users actualUsers = usersPersistence.readFromUsers();

        assertNotNull(actualUsers);
        assertEquals(expectedUsers.getUsers().size(), actualUsers.getUsers().size());
    }
}
