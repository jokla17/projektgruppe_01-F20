package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AuthentificationManager {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        AuthentificationManager.currentUser = currentUser;
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
                    System.out.println("Logged in");
                    loggedIn = true;
                    break;

                } else {
                    System.out.println("Incorrect username or password");
                    loggedIn = false;
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

    public boolean authenticate(int accessLevelUser, int accessLevelClass) {
        if(accessLevelClass <= accessLevelUser){
            System.out.println("Its true.");
            return  true;
        }else{
            System.out.println("Its false");
            return false;
        }
    }


}