package domain;

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
        return App.getDatabaseManager().producerResultSet();
    }

    public List<Systemadministrator> getAdminList() {
        return App.getDatabaseManager().adminResultSet();
    }

    public void createUser(String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        if (!App.getAuthentificationManager().checkPermission()) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        if (employedBy.isEmpty()) {
            App.getDatabaseManager().insertAdmin(new Systemadministrator(username, password, email, firstName, lastName, accessLevel));
        } else {
            App.getDatabaseManager().insertProducer(new Producer(username, password, email, firstName, lastName, accessLevel, employedBy));
        }
    }

    public List<Systemadministrator> readAdmin(String searchText) {
        List<Systemadministrator> searchResultProducer = new ArrayList<>();
        for (int i = 0; i < App.getUserManager().getAdminList().size(); i++) {
            if (App.getUserManager().getAdminList().get(i).toString().toLowerCase().contains(searchText)) {
                searchResultProducer.add(App.getUserManager().getAdminList().get(i));
            }
        }
        return searchResultProducer;
    }

    public List<Producer> readProducer(String searchText) {
        List<Producer> searchResultProducer = new ArrayList<>();
        for (int i = 0; i < App.getUserManager().getProducerList().size(); i++) {
            if (App.getUserManager().getProducerList().get(i).toString().toLowerCase().contains(searchText)) {
                searchResultProducer.add(App.getUserManager().getProducerList().get(i));
            }
        }
        return searchResultProducer;
    }

    public void updateAdmin(Systemadministrator systemadministrator, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        if (!App.getAuthentificationManager().checkPermission()) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        systemadministrator.setUsername(username);
        systemadministrator.setPassword(password);
        systemadministrator.setEmail(email);
        systemadministrator.setFirstName(firstName);
        systemadministrator.setLastName(lastName);
        systemadministrator.setAccessLevel(accessLevel);

        App.getDatabaseManager().updateAdmin(username, password, email, firstName, lastName, accessLevel, systemadministrator.getAdminId());
    }

    public void updateProducer(Producer producer, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        if (!App.getAuthentificationManager().checkPermission()) {
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

        App.getDatabaseManager().updateProducer(username, password, email, firstName, lastName, accessLevel, producer.getProducerId(), employedBy);
    }

    public void deleteAdmin(Systemadministrator sysadmin) {
        if (!App.getAuthentificationManager().checkPermission()) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        App.getDatabaseManager().deleteAdmin(sysadmin.getAdminId());
    }

    public void deleteProducer(User user) {
        if (!App.getAuthentificationManager().checkPermission()) {
            System.out.println("Functionality not available for this user type.");
            return;
        }

        App.getDatabaseManager().deleteProducer(user);
    }
}


