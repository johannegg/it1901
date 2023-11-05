package bookTracker.restserver;

import java.io.IOException;

import org.springframework.stereotype.Service;

import core.User;
import core.Users;
import json.UsersPersistence;

@Service
public class UsersService {
    private Users users;
    private UsersPersistence usersPersistence = new UsersPersistence();

    public Users getUsers() throws IOException {
        return usersPersistence.readFromUsers();
    }

    public void postUser(User user) throws IOException {
        this.users = usersPersistence.readFromUsers();
        System.out.println("test");
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

}
