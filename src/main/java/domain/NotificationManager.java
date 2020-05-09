package domain;

public class NotificationManager {
    public NotificationManager() {
    }

    public String notificationSwitch(String objectName, int type) {
        String notificationMessage;
        switch (type) {
            case 1:
                notificationMessage = objectName + " has been created successfully!";
                break;
            case 2:
                notificationMessage = objectName + " has been updated successfully!";
                break;
            case 3:
                notificationMessage = objectName + " has been deleted successfully!";
                break;
            case 4:
                notificationMessage = "Functionality not available for this user type.";
                break;
            default:
                return "One or more fields are empty or incorrect, please try again.";
        }
        return notificationMessage;
    }
}
