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
     * Creates a new User object
     * 
     * @param email     String email
     * @param username  String username
     * @param password  String password
     */
    public User(String email, String username, String password) {
        setEmail(email);
        setUsername(username);
        setPassword(password);

    }

    /**
     * Sets a new email if email is in valid format
     * 
     * @param email the emial to set
     */
    public void setEmail(String email) {
        checkEmail(email);
        this.email = email;
    }

    /**
     * Checks if the email is in valid format. Which means it contains an '@' and '.'
     * 
     * @param email the email to check
     */
    public void checkEmail(String email){
        if(!(email.contains("@") && email.contains(".")) || email.isEmpty()){
            throw new IllegalArgumentException("The email is invalid");
        }
    }

    /**
     * Sets a new username if the username is not empty
     * 
     * @param username the new username
     */
    public void setUsername(String username) {
        if (username.isEmpty()){
            throw new IllegalArgumentException("You need an username");
        }
        this.username = username;
    }

    /**
     * Sets an new password if it is valid
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

    /**
     * Checks if a password is valid. That means it contains at least eight characters and contains at least 
     * one letter and one number.
     * 
     * @param password
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
    

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public BookShelf getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public String toString() {
    return "username: " + username + ", email: " + email + "password: " + password;
    }

    // public static void main(String[] args) {
    //     User u1 = new User("johanne@ntnu.no", "johannegg", "1234567l");
    //     BookShelf b1 = new BookShelf();
    //     Book book = new Book("book", "austin");
    //     Book book2 = new Book("book2", "ally");
    //     b1.addBook(book);
    //     b1.addBook(book2);
    //     u1.setBookShelf(b1);
    //     System.out.println(u1.getBookShelf());
    // }

}
