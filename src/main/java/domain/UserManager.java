package domain;

import persistence.DatabaseManager;
import presentation.App;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> adminList;
    private List<User> producerList;

    public UserManager() {
        adminList = new ArrayList<>();
        producerList = new ArrayList<>();
        App.getFileManager().readUsers(producerList);
    }

    public List<User> getProducerList() {
        return producerList;
    }

    public List<Systemadministrator> getAdminList() {
        return App.getDatabaseManager().getAdminList();
    }

    public void createUser(String username, String password, String email, String firstName,
                           String lastName, int accessLevel, String... other) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        User user = null;
        switch (accessLevel) {
            case 1:
                user = new Producer(username, password, email, firstName, lastName,
                        accessLevel, generateProducerId(), other[0]);
                producerList.add(user);
                break;
            case 2:
              String adminID = generateAdminId();
                user = new Systemadministrator(username, password, email, firstName, lastName,
                        accessLevel, adminID);
                adminList.add(user);
                App.getDatabaseManager().insertAdmin(new Systemadministrator(username, password, email, firstName, lastName, accessLevel, adminID));
                break;
        }
        //App.getFileManager().appendToFile("users.txt", user);
    }

    public void updateUser(User user, String username, String password, String email, String firstName, String lastName, int accessLevel, String... other) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAccessLevel(accessLevel);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);
        App.getDatabaseManager().updateAdmin(username, password, email, firstName, lastName, accessLevel, ((Systemadministrator) user).getAdminId());
    }

    public void deleteAdmin(Systemadministrator sysadmin) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        adminList.remove(sysadmin);
        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);
        App.getDatabaseManager().deleteAdmin(sysadmin.getAdminId());
    }

    public void deleteProducer(User user) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        producerList.remove(user);
        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);
    }

    public String generateAdminId() {
        int index = 1;
        for (int i = 0; i < adminList.size(); i++) {
            index++;
        }
        return "S" + index;
    }

    public String generateProducerId() {

        int index = 1;
        for (int i = 0; i < producerList.size(); i++) {
            index++;
        }
        return "P" + index;
    }

}


