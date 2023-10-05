package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import json.UserSerializer;

public class Users {
    private List<User> users = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String filePath = "bookTracker/core/src/main/java/resources/users.json";

    public void addUser(User user) throws IOException {
        checkUsername(user);
        this.users.add(user);
        try {
            UserSerializer.serialize(user);
            List<String> userList = readFromUsers();

            // TODO: Make better later
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            ArrayNode usersNode = (ArrayNode) rootNode.get("users");
            String newUser = user.getUsername();
            usersNode.add(newUser);
            objectMapper.writeValue(new File(filePath), rootNode);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

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
            return null;
        }
    }

    private void checkUsername(User user) throws IOException {
        List<String> userList = readFromUsers();
    
        for (String u : userList) {
            if (u.equals(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
        }
    }

    public User getUser(String email) {
        for (User user : this.users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return "" + users.stream().map(user -> user.toString()).collect(Collectors.toList());
    }

    public List<User> getUsers() {
        return this.users;
    }

    public static void main(String[] args) {
        // Users users = new Users();
        // User u1 = new User("johanne@ntnu.no", "johannegg", "1234567l");
        // User u2 = new User("per@ntnu.no", "per123", "1234569l");
        // //users.addUser(u1);
        // //users.addUser(u2);
        // System.out.println(users.getUsers());
    }

}
