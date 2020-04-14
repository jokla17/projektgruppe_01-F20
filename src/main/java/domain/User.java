package domain;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int accessLevel;

    public User(String username, String password, String email, String firstName, String lastName, int accessLevel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel(){
        //not implemented yet
        return accessLevel;
    }

    public void setAccessLevel(){
        //not implemented yet
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return username + ";" + password + ";" + email + ";" + firstName + ";" + lastName + ";" + accessLevel;
    }
}
