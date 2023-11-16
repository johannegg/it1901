package ui;

import java.io.IOException;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;

/**
 * Interface for data access operations in the app.
 * Connects user and book data stores.
 */
public interface DataAccess {

    /**
     * Geets a user by their username.
     *
     * @param username the username of the user.
     * @return the User object if found, or null if not found.
     */
    public User getUserByUsername(String username);

    /**
     * Gets the currently logged in user.
     *
     * @return the currently logged in User object, or null if no user is logged in.
     */
    public User getLoggedInUser();

    /**
     * Gets the collection of all users.
     *
     * @return a Users object containing all the User objects.
     */
    public Users getUsers();

    /**
     * Adds a new user or updates an existing user in the data store.
     *
     * @param user the user to add or update.
     * @throws IOException if there is an issue writing the user data to the
     *                     storage.
     */
    public void postUser(User user) throws IOException;

    /**
     * Updates an existing user's details in the data store.
     * If a user with the same username exists, it is replaced with input user
     * object.
     *
     * @param user the user object with updated details.
     * @throws IOException if there is an issue writing the updated user data to the
     *                     storage.
     */
    public void putUser(User user) throws IOException;

    /**
     * Gets a book by its ID.
     *
     * @param bookId the ID of the book to retrieve.
     * @return the Book object if found, or null if no book with the given ID
     *         exists.
     * @throws IOException if there is an issue accessing the book data.
     */
    public Book getBookById(String bookId) throws IOException;

    /**
     * Gets the entire library of books.
     *
     * @return a BookShelf object containing all books in the library.
     * @throws IOException if there is an issue accessing the library data.
     */
    public BookShelf getLibrary() throws IOException;

}