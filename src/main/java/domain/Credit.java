package domain;

public class Credit {
    private String creditId;
    private String creditRole;
    private String creditName;

    public Credit(String creditId, String creditRole, String creditName) {
        this.creditId = creditId;
        this.creditRole = creditRole;
        this.creditName = creditName;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getCreditRole() {
        return creditRole;
    }

    public void setCreditRole(String creditRole) {
        this.creditRole = creditRole;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    @Override
    public String toString() {
        return creditId + ";" + creditRole + ";" + creditName;
    }
}
