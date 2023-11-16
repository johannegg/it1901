package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to handle the users.The users are saved in an ArrayList as
 * User-objects. The class has methods to save, add, remove and get users.
 */
public class Users implements Iterable<User> {

    private List<User> users = new ArrayList<>();

    /**
     * Method to add user
     * 
     * @param user the user to add
     * @throws IOException if checkUsername, createUser or writeToUsers throws
     *                     an exception
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Checks if username is already used.
     * 
     * @param username the username to check.
     * @throws IllegalArgumentException if username already exists.
     */
    public void checkUsername(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                throw new IllegalArgumentException("Username already exists");
            }
        }
    }

    /**
     * Gets an User object using an username
     * 
     * @param username the username to use
     * @return the user, or null if it does not exist
     */
    public User getUser(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Method to remove user
     * 
     * @param user the user to remove
     */
    public void removeUser(User user) {
        this.users.remove(user);
    }

    /**
     * Method for getting a list of users
     * 
     * @return a list of Users
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Makes an iterator over the users in users list.
     * 
     * @return a users list iterator.
     */
    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

}
