package domain;

public class Systemadministrator extends User {
    private int adminId;
    private String name;

    public Systemadministrator(String username, String password, String email, String firstName, String lastName, int accessLevel, int adminId, String name) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.name = name;
        this.adminId = adminId;
    }
}
