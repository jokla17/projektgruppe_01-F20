package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuthentificationManager {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean login(String username, String password) {
        Scanner sc = null;
        boolean loggedIn = false;
        try {
            sc = new Scanner(new File("users.txt"));
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                String user = split[0];
                String pass = split[1];
                if (user.equals(username) & pass.equals(password)) {
                    loggedIn = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
        return loggedIn;
    }

    public void logout() {
        //not implemented yet
    }

    public void signUp() {

    }
}