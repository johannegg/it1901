package ui;

import java.io.IOException;

import core.Book;
import core.User;
import core.Users;

public interface DataAccess {
    
    public User getUserByUsername(String username);

    public User getLoggedInUser();

    public Users getUsers();

    public void postUser(User user) throws IOException;

    public void putUser(User user) throws IOException;

    public Book getBookById(String bookId) throws IOException;

}
