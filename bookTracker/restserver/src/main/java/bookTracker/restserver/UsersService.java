package bookTracker.restserver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import core.User;
import core.Users;
import json.UsersPersistence;

@Service
public class UsersService {
    private Users users;
    private User user;
    private UsersPersistence usersPersistence = new UsersPersistence();
    private String usersFileName = "users.json";

    public Users getUsers() throws IOException {
        return usersPersistence.readFromUsers();
    }

    public void putUser(User user) throws IOException {
        this.users = usersPersistence.readFromUsers();
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

  }

