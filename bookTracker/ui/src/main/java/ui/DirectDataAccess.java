package ui;

import java.io.File;

import core.User;
import core.Users;
import json.UsersPersistence;

public class DirectDataAccess implements DataAccess{
    
    private UsersPersistence usersPersistence = new UsersPersistence();
    private Users users;

    public DirectDataAccess() {
        this.users = readUsers();
        usersPersistence.setFile(new File("../ui/src/main/java/resources/test/test_users.json"));
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getLoggedInUser() {
        for (User user : users) {
            if (user.isLoggedIn()) {
                return user;
            }
        }
        return null;
    }

    public Users getUsers() {
        return this.users;
    }

    public void postUser(User user) {
        this.users.addUser(user);
    }

    public void putUser(User user) {
        User oldUser = users.getUser(user.getUsername());
        this.users.removeUser(oldUser);
        this.users.addUser(user);
    }

    public Users readUsers() {
        try {
            users = usersPersistence.readFromUsers();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Unable to read from users.json");
        }
    }

}
