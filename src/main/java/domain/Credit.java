package domain;

public class Credit {
    private int id;
    private String role;
    private String firstName;
    private String lastName;

    public Credit(int Id, String Role, String firstName, String lastName) {
        this.id = Id;
        this.role = Role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Credit(String Role, String firstName, String lastName) {
        this.role = Role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id + ";" + role + ";" + firstName + ";" + lastName;
    }
}
