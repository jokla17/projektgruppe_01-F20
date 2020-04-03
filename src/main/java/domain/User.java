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

    public void getAccessLevel(){
        //not implemented yet
    }

    public void setAccessLevel(){
        //not implemented yet
    }
}
