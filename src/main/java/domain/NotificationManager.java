package domain;

public class NotificationManager {
    public NotificationManager() {}

    public String notificationSwitch(String objectName, int type) {
        String notificationMessage = "has been ";
        switch (type) {
            case 1:
                notificationMessage = objectName + " created";
                break;
            case 2:
                notificationMessage = objectName + " updated";
                break;
            case 3:
                notificationMessage = objectName + " deleted";
                break;
            default:
                return "One or more fields are empty or incorrect, please try again.";
        }
        return notificationMessage + " successfully!";
    }
}
