package ui;

import java.io.File;
import java.io.IOException;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;
import json.LibraryPersistence;
import json.UsersPersistence;

public class DirectDataAccess implements DataAccess {

    private UsersPersistence usersPersistence;
    private LibraryPersistence libraryPersistence;
    private Users users;

    public DirectDataAccess() {
        this.usersPersistence = new UsersPersistence();
        this.libraryPersistence = new LibraryPersistence();
        this.usersPersistence.setFile(new File("../ui/src/test/java/ui/resources/test_users.json"));
        this.users = readUsers();
    }

    public DirectDataAccess(User user) throws IOException {
        this.usersPersistence = new UsersPersistence();
        this.libraryPersistence = new LibraryPersistence();
        this.usersPersistence.setFile(new File("../ui/src/test/java/ui/resources/test_users.json"));
        this.users = readUsers();
        user.setLoggedIn(true); // Setter brukeren som logget inn
        if (user.getBookShelf() == null) {
            user.setBookShelf(new BookShelf());
        }
        this.postUser(user); // Legger til brukeren
        this.users = readUsers(); // Leser brukerne på nytt
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
        // readUsers();
        return this.users;
    }

    public void postUser(User user) throws IOException {
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

    public void putUser(User user) throws IOException {
        User oldUser = users.getUser(user.getUsername());
        this.users.removeUser(oldUser);
        this.users.addUser(user);
        usersPersistence.writeToUsers(users);
    }

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

    public BookShelf getLibrary() throws IOException {
        return libraryPersistence.readFromLibrary();
    }

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

    // for testing
    public void deleteAllUsers() throws IOException {
        Users emptyUsers = new Users();
        usersPersistence.writeToUsers(emptyUsers);
    }

}