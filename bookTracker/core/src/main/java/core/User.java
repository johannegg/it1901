package core;


/**
 * User class. Each User has an email, username and password.
 */
public class User {
    private String email;
    private String username;
    private String password;
    private boolean loggedIn;
    private BookShelf bookShelf;

    
    /**
     * Creates a new User object and sets every parameter to null.
     */
    public User(){
        
    }

    /**
     * Creates a new User object.
     * 
     * @param email     String email.
     * @param username  String username.
     * @param password  String password.
     */
    public User(String email, String username, String password) {
        setEmail(email);
        setUsername(username);
        setPassword(password);

    }

    /**
     * Sets a new email if email is valid.
     * 
     * @param email the email to set.
     */
    public void setEmail(String email) {
        checkEmail(email);
        this.email = email;
    }

    /**
     * Checks if the email is valid. A valid email must contain both '@' and '.' symbols.
     * 
     * @param email the email to check.
     * @throws IllegalArgumentException if email is invalid or empty.
     */
    public void checkEmail(String email){
        if(!(email.contains("@") && email.contains(".")) || email.isEmpty()){
            throw new IllegalArgumentException("The email is invalid");
        }
    }

    /**
     * Sets a new username if the username is not empty.
     * 
     * @param username the new username.
     * @throws IllegalArgumentException if username is empty.
     */
    public void setUsername(String username) {
        if (username.isEmpty()){
            throw new IllegalArgumentException("You need an username");
        }
        this.username = username;
    }

    /**
     * Sets a new password if it is valid.
     * 
     * @param password the password to set.
     */
    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

    /**
     * Checks if a password is valid. That means it contains at least eight characters and contains at least 
     * one letter and one number.
     * 
     * @param password the password to check
     * @throws IllegalArgumentException if password is invalid. 
     */
    public void checkPassword(String password){
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password requires at least 8 characters");
        }
        int numberOfDigits = 0;
            char[] chars = password.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)){
                    numberOfDigits ++;
                }
            }
        if (numberOfDigits < 1 || numberOfDigits == password.length()){
            throw new IllegalArgumentException("Password requires at least one letter and one digit");
        }
    }
    
    /**
     * Gets email.
     * 
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets username.
     * 
     * @return user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password. 
     * 
     * @return user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if user is logged in. 
     * 
     * @return true if user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets user as logged in. 
     * 
     * @param loggedIn true if the user is logged in.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Gets the user's bookshelf. 
     * 
     * @return a book list.
     */
    public BookShelf getBookShelf() {
        return bookShelf;
    }

    /**
     * Sets user's bookshelf.
     * 
     * @param bookShelf the users book list.
     */
    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

}
