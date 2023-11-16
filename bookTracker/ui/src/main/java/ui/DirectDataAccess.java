package ui;

import java.io.File;
import java.io.IOException;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;
import json.LibraryPersistence;
import json.UsersPersistence;

/**
 * Provides direct access to user and library data for the app.
 * Class for operations related to persisting and retriving user and book data.
 */
public class DirectDataAccess implements DataAccess {

    private UsersPersistence usersPersistence;
    private LibraryPersistence libraryPersistence;
    private Users users;

    /**
     * Constructor for DirectDataAccess object.
     * Initializes persistence layers and loads existing users from test_users.json.
     */
    public DirectDataAccess() {
        this.usersPersistence = new UsersPersistence();
        this.libraryPersistence = new LibraryPersistence();
        this.usersPersistence.setFile(new File("../ui/src/test/java/ui/resources/test_users.json"));
        this.users = readUsers();
    }

    /**
     * Constructor for DirectDataAccess object with a specified user.
     * Initializes persistence layers, loads existing users, sets the specified user
     * as logged in, and updates the user data.
     *
     * @param user the user to be set as logged in and added to the data store.
     * @throws IOException if there is an issue reading or writing user data.
     */
    public DirectDataAccess(User user) throws IOException {
        this.usersPersistence = new UsersPersistence();
        this.libraryPersistence = new LibraryPersistence();
        this.usersPersistence.setFile(new File("../ui/src/test/java/ui/resources/test_users.json"));
        this.users = readUsers();
        user.setLoggedIn(true);
        if (user.getBookShelf() == null) {
            user.setBookShelf(new BookShelf());
        }
        this.postUser(user);
        this.users = readUsers();
    }

    /**
     * Gets a user by their username.
     *
     * @param username the username of the user to retrieve.
     * @return the User object if found, or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Gets the currently logged in user.
     *
     * @return the currently logged in User object, or null if no user is logged in.
     */
    public User getLoggedInUser() {
        for (User user : users) {
            if (user.isLoggedIn()) {
                return user;
            }
        }
        return null;
    }

    /**
     * Gets all users.
     *
     * @return a Users object containing all the users.
     */
    public Users getUsers() {
        return this.users;
    }

    /**
     * Adds a new user or updates an existing user in the data store.
     *
     * @param user the user to add or update.
     * @throws IOException if there is an issue writing the user data to the
     *                     storage.
     */
    public void postUser(User user) throws IOException {
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    /**
     * Updates an existing user's details in the data store.
     * If a user with the same username exists, it is replaced with new
     * user object.
     *
     * @param user the user object with updated details.
     * @throws IOException if there is an issue writing the updated user data to the
     *                     storage.
     */
    public void putUser(User user) throws IOException {
        User oldUser = users.getUser(user.getUsername());
        this.users.removeUser(oldUser);
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    /**
     * Reads and returns the list of users.
     * 
     * @return a Users object containing the list of User objects.
     * @throws IllegalStateException if there is an issue reading users from the
     *                               storage.
     */
    public Users readUsers() {
        try {
            users = usersPersistence.readFromUsers();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw new IllegalStateException("Unable to read from test_users.json");
        }
    }

    /**
     * Gets the entire library of books.
     *
     * @return a BookShelf object containing all books in the library.
     * @throws IOException if there is an issue reading the library data.
     */
    public BookShelf getLibrary() throws IOException {
        return libraryPersistence.readFromLibrary();
    }

    /**
     * Gets a book by its ID from the library.
     *
     * @param bookId the book ID to retrieve.
     * @return the Book object, or null if no book with the given ID exists.
     * @throws IOException if there is an issue reading the library data.
     */
    @Override
    public Book getBookById(String bookId) throws IOException {
        BookShelf bookShelf = libraryPersistence.readFromLibrary();
        for (Book book : bookShelf) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Deletes all users from the storage, primarily for testing purposes.
     *
     * @throws IOException if there is an issue writing the updated data to the
     *                     storage.
     */
    public void deleteAllUsers() throws IOException {
        Users emptyUsers = new Users();
        usersPersistence.writeToUsers(emptyUsers);
    }

}