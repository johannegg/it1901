package json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import core.User;

/**
 * Persistence class for reading and writing to users
 */
public class UsersPersistence {

    private String filePath = "../core/src/main/java/resources/users.json";
    private String startFilePath = "../core/src/main/java/resources/";
    private ObjectMapper objectMapper;

    /**
     * Creates a new UsersPersistence instance and sets the objectMapper
     */
    public UsersPersistence() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Finds the password field from the given json file and returns it as a string
     * 
     * @param filePath the path to the json file
     * @return the password. Null if it does not exist.
     */
    public String readFromUser(String filePath){
        File jsonFile = new File(filePath);
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonFile);
            JsonNode usersString = jsonNode.get("password");
            return usersString.asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Reads from the users field in a json file and returns it as a list of usernames.
     * 
     * @return a list of usernames or an empty list if something goes wrong.
     */
    public List<String> readFromUsers() {
        File jsonFile = new File(filePath);
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonFile);
            JsonNode usersArray = jsonNode.get("users");
            List<String> userList = new ArrayList<>();
            if (usersArray.isArray()) {
                for (JsonNode element : usersArray) {
                    userList.add(element.asText());
                }
            }
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new user by creating a new json file for it. Takes in a user and uses the username as filename.
     * 
     * @param user the user to create
     * @throws StreamWriteException
     * @throws DatabindException
     * @throws IOException
     */
    public void createNewUser(User user) throws StreamWriteException, DatabindException, IOException{
        String username = user.getUsername();
        objectMapper.writeValue(new File(startFilePath + username + ".json"), user);
    }

    /**
     * Takes in an user and adds it to the users field in the json file.
     * @param user
     * @throws IOException
     */
    public void writeToUsers(User user) throws IOException {
        JsonNode rootNode = objectMapper.readTree(new File(filePath));
        ArrayNode usersNode = (ArrayNode) rootNode.get("users");
        String newUser = user.getUsername();
        usersNode.add(newUser);
        objectMapper.writeValue(new File(filePath), rootNode);
    }
    
    /**
     * Sets the filepaths. The reason it was added was so that it was possible to test the class without messing with the users.json 
     * file and creating new user files.
     * 
     * @param testFilePath the file path to a json file with a list of users
     * @param startTestFilePath the beginning of a fil epath 
     */
    public void setFilePath(String testFilePath, String startTestFilePath){
        this.filePath = testFilePath;
        this.startFilePath = startTestFilePath;
    }
}
