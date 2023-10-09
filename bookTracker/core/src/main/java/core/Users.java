package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import json.UsersPersistence;

public class Users {
    private List<User> users = new ArrayList<>();
    private UsersPersistence usersPersistence = new UsersPersistence();

    public void addUser(User user) throws IOException {
        checkUsername(user);
        this.users.add(user);
        usersPersistence.createNewUser(user);
        usersPersistence.writeToUsers(user);
    }

    /**
     * Method to add users without actually saving them in json.
     * It is only here temporary until we move the persistence elsewhere
     * 
     * @param user User user
     */
    public void addUserForTest(User user) throws IOException {
        checkUsername(user);
        this.users.add(user);
    }

    private void checkUsername(User user) throws IOException {
        List<String> userList = usersPersistence.readFromUsers();

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
        // users.addUser(u1);
        // users.addUser(u2);
        // System.out.println(users.getUsers());
    }

}
