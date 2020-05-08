package domain;

import presentation.App;

public class AuthentificationManager {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean login(String username, String password) {
        boolean loggedIn = false;
        for (int i = 0; i < App.getDatabaseManager().getAdminList().size(); i++) {
            String currentUsername = App.getDatabaseManager().getAdminList().get(i).getUsername();
            String currentPassword = App.getDatabaseManager().getAdminList().get(i).getPassword();
            if (currentUsername.equals(username) && currentPassword.equals(password)) {
                loggedIn = true;
            }
        }
        return loggedIn;
    }
}