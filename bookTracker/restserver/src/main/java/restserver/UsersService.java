package restserver;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import core.User;
import core.Users;
import json.UsersPersistence;

/**
 * Service class for managing users in application.
 */
@Service
public class UsersService {

    private Users users;
    private UsersPersistence usersPersistence = new UsersPersistence();

    /**
     * Retrieves a list of all users, by using usersPersistence to read from json
     * file.
     *
     * @return A Users object.
     * @throws IOException if the reading of book data using usersPersistence fails.
     */
    public Users getUsers() throws IOException {
        return usersPersistence.readFromUsers();
    }

    /**
     * Adds a new user by using usersPersistence to read and write.
     * 
     * @param user the new user to add.
     * @throws IOException if the reading or writing of book data using
     *                     usersPersistence fails.
     */
    public void postUser(User user) throws IOException {
        this.users = usersPersistence.readFromUsers();
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    /**
     * Updates an already existing user by using usersPersistence to read and write.
     * 
     * @param user the User object to update.
     * @throws IOException if the reading or writing of book data fails
     */
    public void putUser(User user) throws IOException {
        this.users = usersPersistence.readFromUsers();
        User oldUser = users.getUser(user.getUsername());
        this.users.removeUser(oldUser);
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    /**
     * Sets the file name for storing data. Used for testing.
     * 
     * @param fileName the name of the json file storing users data.
     */
    public void setUsersJsonFileName(String fileName) {
        this.usersPersistence.setFile(new File(fileName));
    }

    /**
     * Deletes all users, by using usersPersistense to write.
     * 
     * @throws IOException if the deleting fails.
     */
    public void deleteUsers() throws IOException {
        usersPersistence.writeToUsers(new Users());
    }

}
