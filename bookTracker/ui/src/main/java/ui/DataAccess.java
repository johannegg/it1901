package ui;

import core.User;
import core.Users;

public interface DataAccess {
    
    public User getUserByUsername(String username);

    public User getLoggedInUser();

    public Users getUsers();

    public void postUser(User user);

    public void putUser(User user);

}
