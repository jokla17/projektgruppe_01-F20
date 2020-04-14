package domain;

public class Producer extends User {
    private String producerId;
    private String employedBy;

    public Producer(String username, String password, String email, String firstName, String lastName,
                    int accessLevel, String producerId, String employedBy) {
        super(username, password, email, firstName, lastName, accessLevel);
        this.producerId = producerId;
        this.employedBy = employedBy;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getEmployedBy() {
        return employedBy;
    }

    public void setEmployedBy(String employedBy) {
        this.employedBy = employedBy;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + employedBy + ";" + producerId;
    }
}
