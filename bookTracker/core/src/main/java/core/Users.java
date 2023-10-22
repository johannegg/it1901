package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addUser(User user) throws IOException {
        // checkUsername(user);
        this.users.add(user);
        // usersPersistence.createNewUser(user);
        // usersPersistence.writeToUsers(user);
    }

    /**
     * Method to add users without actually saving them in json.
     * It is only here temporary until we move the persistence elsewhere
     * 
     * @param user User user
     */
    public void addUserForTest(User user) throws IOException {
        // checkUsername(user);
        this.users.add(user);
    }

    /**
     * Gets an User object using an email address
     * 
     * @param email the email address to use
     * @return the user, or null if it does not exist
     */
    public User getUser(String email) {
        for (User user : this.users) {
            if (user.getEmail().equals(email)) {
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
     * ToString method for Users
     */
    @Override
    public String toString() {
        return "" + users.stream().map(user -> user.toString()).collect(Collectors.toList());
    }

    /**
     * Method for getting a list of users
     * 
     * @return a list of Users
     */
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
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
