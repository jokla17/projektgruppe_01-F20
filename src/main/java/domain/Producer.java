package domain;

public class Producer extends User {
    private int producerId;
    private String name;
    private String employedBy;

    public Producer(String username, String password, String email, String firstName, String lastName, int accessLevel, int producerId, String name, String employedBy) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.producerId = producerId;
        this.name = name;
        this.employedBy = employedBy;
    }
}
