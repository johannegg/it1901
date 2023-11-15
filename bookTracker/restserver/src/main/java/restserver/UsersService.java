package restserver;

import java.io.File;
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
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    public void putUser(User user) throws IOException {
        this.users = usersPersistence.readFromUsers();
        User oldUser = users.getUser(user.getUsername());
        this.users.removeUser(oldUser);
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    public void setUsersJsonFileName(String fileName) {
        this.usersPersistence.setFile(new File(fileName));
    }

    public void deleteUsers() throws IOException {
        usersPersistence.writeToUsers(new Users());
    }

}
