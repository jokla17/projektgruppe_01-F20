package domain;

public class NotificationManager {
    public NotificationManager() {
    }

    public String notificationSwitch(String objectName, int type) {
        String notificationMessage;
        switch (type) {
            case 1:
                notificationMessage = objectName + " er blevet oprettet!";
                break;
            case 2:
                notificationMessage = objectName + " er blevet opdateret";
                break;
            case 3:
                notificationMessage = objectName + " er blevet slettet!";
                break;
            case 4:
                notificationMessage = "Funktionalitet er ikke tilgængeligt til denne brugertype.";
                break;
            case 5:
                notificationMessage = "Der skal være valgt et element i tabellen før der kan fjernes!";
                break;
            case 6:
                notificationMessage = "Der skal være valgt et element i tabellen før der kan opdateres!";
                break;
            case 7:
                notificationMessage = "Produktion kunne ikke fjernes, grundet den har tilknyttet krediteringer.";
                break;
            case 8:
                notificationMessage = "En fil er blevet oprettet!";
                break;
            case 9:
                notificationMessage = "To krediteringer kan ikke oprettes med samme navn.";
                break;
            default:
                return "En eller flere felter er tomme eller har fejl, prøv venligst igen.";
        }
        return notificationMessage;
    }
}
