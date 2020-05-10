package domain;

public class NotificationManager {
    public NotificationManager() {
    }

    public String notificationSwitch(String objectName, int type) {
        String notificationMessage;
        switch (type) {
            case 1:
                notificationMessage = objectName + " er blevet oprettet uden fejl!";
                break;
            case 2:
                notificationMessage = objectName + " er blevet opdateret uden fejl!";
                break;
            case 3:
                notificationMessage = objectName + " er blevet slettet uden fejl!";
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
            default:
                return "En eller flere felter er tomme eller har fejl, prøv venligst igen.";
        }
        return notificationMessage;
    }
}
