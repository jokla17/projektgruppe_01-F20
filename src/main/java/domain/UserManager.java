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
    }

    public List<Producer> getProducerList() {
        return App.getDatabaseManager().getProducerList();
    }

    public List<Systemadministrator> getAdminList() {
        return App.getDatabaseManager().getAdminList();
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
                adminList.add(admin);
                App.getDatabaseManager().insertAdmin(new Systemadministrator(username, password, email, firstName, lastName, accessLevel, adminID));
                break;
        }
    }

    public void updateAdmin(Systemadministrator systemadministrator, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        systemadministrator.setUsername(username);
        systemadministrator.setPassword(password);
        systemadministrator.setEmail(email);
        systemadministrator.setFirstName(firstName);
        systemadministrator.setLastName(lastName);
        systemadministrator.setAccessLevel(accessLevel);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(adminList);
        tempList.addAll(producerList);
        App.getDatabaseManager().updateAdmin(username, password, email, firstName, lastName, accessLevel, systemadministrator.getAdminId());
    }

    public void updateProducer(Producer producer, String username, String password, String email,
                               String firstName, String lastName, int accessLevel, String employedBy) {
        if (App.getAuthentificationManager().getCurrentUser().getAccessLevel() == 1) {
            System.out.println("Functionality not available for this user type.");
            return;
        }
        producer.setUsername(username);
        producer.setPassword(password);
        producer.setEmail(email);
        producer.setFirstName(firstName);
        producer.setLastName(lastName);
        producer.setAccessLevel(accessLevel);
        producer.setEmployedBy(employedBy);

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(producerList);
        App.getDatabaseManager().updateProducer(username, password, email, firstName, lastName, accessLevel, producer.getProducerId(), employedBy);
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


