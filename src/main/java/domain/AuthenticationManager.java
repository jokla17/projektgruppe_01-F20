package domain;

import presentation.App;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {
    private User currentUser;
    private List<User> users;

    public AuthenticationManager() {
        users = new ArrayList<>();
        users.addAll(App.getUserManager().getAdminList());
        users.addAll(App.getUserManager().getProducerList());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(String username, String password) {
        boolean loggedIn = false;
        for (User u: users) {
            if (username.equals(u.getUsername()) & password.equals(u.getPassword())) {
                currentUser = u;
                loggedIn = true;
            }
        }
        return loggedIn;
    }

    public boolean checkPermission() {
        int accessLevel = currentUser.getAccessLevel();

        boolean permission = false;
        switch (accessLevel) {
            case 1:
                permission = true;
                break;
            case 2:
                permission = false;
                break;
        }
        return permission;
    }
}