package core;


public class User {
    private String email;
    private String username;
    private String password;

    public User(){
        
    }

    public User(String email, String username, String password) {
        setEmail(email);
        setUsername(username);
        setPassword(password);

    }

    public void setEmail(String email) {
        checkEmail(email);
        this.email = email;
    }

    public void checkEmail(String email){
        if(!(email.contains("@") && email.contains(".")) || email.isEmpty()){
            throw new IllegalArgumentException("The email is invalid");
        }
    }

    public void setUsername(String username) {
        if (username.isEmpty()){
            throw new IllegalArgumentException("You need an username");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

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
            throw new IllegalArgumentException("Password requires at least one character or one digit");
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

    @Override
    public String toString() {
    return "username: " + username + ", email: " + email + "password: " + password;
    }

    // public static void main(String[] args) {
    //     User u1 = new User("johanne@ntnu.no", "johannegg", "1234567l");
    //     System.out.println(u1.getPassword());
    // }

}
