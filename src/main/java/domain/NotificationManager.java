package domain;

public class NotificationManager {
    public NotificationManager() {
    }

    public String notificationSwitch(String objectName, int type) {
        String notificationMessage;
        switch (type) {
            case 1:
                notificationMessage = "has been " + objectName + " created successfully!";
                break;
            case 2:
                notificationMessage = "has been " + objectName + " updated successfully!";
                break;
            case 3:
                notificationMessage = "has been" + objectName + " deleted successfully!";
                break;
            default:
                return "One or more fields are empty or incorrect, please try again.";
        }
        return notificationMessage;
    }
}
