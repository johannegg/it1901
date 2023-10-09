package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.User;


public class UsersPersistenceTest {
    private UsersPersistence usersPersistence;
    private String testUsersFilePath = "../core/src/test/resources/testUsers.json";
    private String testFilePath = "../core/src/test/resources/testPersistence.json";
    private String startTestFilePath = "../core/src/test/resources/";
    private String invalidFilePath = "/wrong.json";
    private ObjectMapper objectMapper;
    private User user;

    @BeforeEach
    public void setUp() throws IOException{
        usersPersistence = new UsersPersistence();
        usersPersistence.setFilePath(testUsersFilePath, startTestFilePath);
        objectMapper = new ObjectMapper();
        user = new User("johanne@ntnu.no", "johanne123", "jegerkul123");
        createTestJsonFile("{\"email\":\"test@email\",\"username\":\"test\",\"password\":\"test123\"}", testFilePath);
    }

    /**
     * Help method to create a test persistence class
     * @param jsonContent
     * @throws IOException
     */
    private void createTestJsonFile(String jsonContent, String filePath) throws IOException {
        File testFile = new File(filePath);
        objectMapper.writeValue(testFile, objectMapper.readTree(jsonContent));
    }

    @Test
    public void testReadFromUser(){
        String password = usersPersistence.readFromUser(testFilePath);
        assertEquals("test123", password);
    }

    @Test
    public void testReadFromUserWithInvalidPath() {
        String password = usersPersistence.readFromUser(invalidFilePath);
        assertNull(password);
    }

    @Test
    public void testCreateNewUser() throws StreamWriteException, DatabindException, IOException{
        usersPersistence.createNewUser(user);
        assertEquals("jegerkul123", usersPersistence.readFromUser(startTestFilePath + user.getUsername() + ".json"));
    }

    @Test
    public void testWriteToUsers() throws IOException{
        createTestJsonFile("{\"users\":[\"test1\",\"test2\"]}", "../core/src/test/resources/testUsers.json");
        usersPersistence.writeToUsers(user);
    }


}
