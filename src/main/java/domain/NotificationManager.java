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
                notificationMessage = "Funktionalitet er ikke tilgængeligt for denne brugertype.";
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
            case 10:
                notificationMessage = "To brugere med samme brugernavn eller email kan ikke oprettes.";
                break;
            case 11:
                notificationMessage = "Brugeren kunne ikke slettes, grundet den har tilknyttede produktioner.";
                break;
            case 12:
                notificationMessage = "Opdatering mislykkedes, grundet en anden bruger har samme brugernavn eller email.";
                break;
            case 13:
                notificationMessage = "Oprettelse af fil mislykkedes.";
                break;
            default:
                return "En eller flere felter er tomme eller har fejl, prøv venligst igen.";
        }
        return notificationMessage;
    }
}
