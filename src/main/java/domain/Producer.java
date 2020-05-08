package domain;

public class Producer extends User {
    private int producerId;
    private String employedBy;

    public Producer(String username, String password, String email, String firstName, String lastName,
                    int accessLevel, String employedBy) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.employedBy = employedBy;
    }

    public Producer(String username, String password, String email, String firstName, String lastName, int accessLevel, int producerId, String employedBy) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.producerId = producerId;
        this.employedBy = employedBy;
    }

    public int getProducerId() {
        return producerId;
    }

    public String getEmployedBy() {
        return employedBy;
    }

    public void setEmployedBy(String employedBy) {
        this.employedBy = employedBy;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + producerId + ";" + employedBy;
    }
}
