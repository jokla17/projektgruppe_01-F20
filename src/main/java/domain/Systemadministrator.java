package domain;

public class Systemadministrator extends User {
    private String adminId;


    public Systemadministrator(String username, String password, String email, String firstName,
                               String lastName, int accessLevel, String adminId) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.adminId = adminId;
    }
}
