package domain;

public class Systemadministrator extends User {
    private int adminId;

    public Systemadministrator(String username, String password, String email, String firstName,
                               String lastName, int accessLevel) {
        super(username, password, email, firstName, lastName, accessLevel);
    }

    public Systemadministrator(int adminId, String username, String password, String email, String firstName,
                               String lastName, int accessLevel) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + adminId;
    }
}
