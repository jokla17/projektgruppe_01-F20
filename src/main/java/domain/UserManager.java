package domain;

import persistence.DatabaseManager;
import presentation.App;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<Systemadministrator> adminList;
    private List<Producer> producerList;

    public UserManager() {
        adminList = new ArrayList<>();
        producerList = new ArrayList<>();
        App.getFileManager().readUsers(producerList);
//        App.getFileManager().readUsers(adminList, producerList);
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public List<Systemadministrator> getAdminList() {
        return App.getDatabaseManager().getAdminList();
        return adminList;
    }

    public void createUser(String username, String password, String email, String firstName,
                           String lastName, int accessLevel, String employedBy) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        Systemadministrator admin = null;
        Producer producer = null;

        switch (accessLevel) {
            case 1:
                String producerID = generateProducerId();
                producer = new Producer(username, password, email, firstName, lastName,
                        accessLevel, producerID, employedBy);
                producerList.add(producer);
                App.getDatabaseManager().insertProducer(username, password, email, firstName, lastName, accessLevel, producerID, employedBy);
                break;
            case 2:
              String adminID = generateAdminId();
                admin = new Systemadministrator(username, password, email, firstName, lastName,
                        accessLevel, adminID);
                adminList.add(user);
                App.getDatabaseManager().insertAdmin(new Systemadministrator(username, password, email, firstName, lastName, accessLevel, adminID));
                break;
        }
        //App.getFileManager().appendToFile("users.txt", user);
                adminList.add(admin);
                App.getDatabaseManager().insertAdmin(username, password, email, firstName, lastName, accessLevel, adminID);
                break;
        }
        //App.getFileManager().appendToFile("users.txt", admin, producer);
    }

    public void updateUser(User user, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
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
        user.setEmployedBy(employedBy);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);
        App.getDatabaseManager().updateAdmin(username, password, email, firstName, lastName, accessLevel, ((Systemadministrator) user).getAdminId());
    }

    public void deleteAdmin(Systemadministrator sysadmin) {

//        App.getDatabaseManager().updateProducer(username, password, email, firstName, lastName, accessLevel, employedBy);
    }

    public void updateProducer(User user, String username, String password, String email,
                               String firstName, String lastName, int accessLevel, String employedBy) {
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
        user.setEmployedBy(employedBy);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(producerList);
        App.getFileManager().writeToFile("users.txt", tempList);

        App.getDatabaseManager().updateProducer(username, password, email, firstName, lastName, accessLevel, ((Producer)user).getProducerId(), employedBy);

    }


    public void deleteAdmin(User user) {
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

        App.getDatabaseManager().deleteProducer(user);
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


