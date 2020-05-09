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
        if (employedBy.isEmpty()) {
            App.getDatabaseManager().insertAdmin(new Systemadministrator(username, password, email, firstName, lastName, accessLevel));
        } else {
            App.getDatabaseManager().insertProducer(new Producer(username, password, email, firstName, lastName, accessLevel, employedBy));
        }
    }

    public List<Systemadministrator> readAdmin(String searchText) {
        List<Systemadministrator> searchResultProducer = new ArrayList<>();
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).toString().toLowerCase().contains(searchText)) {
                searchResultProducer.add(adminList.get(i));
            }
        }
        return searchResultProducer;
    }

    public List<Producer> readProducer(String searchText) {
        List<Producer> searchResultProducer = new ArrayList<>();
        for (int i = 0; i < producerList.size(); i++) {
            if (producerList.get(i).toString().toLowerCase().contains(searchText)) {
                searchResultProducer.add(producerList.get(i));
            }
        }
        return searchResultProducer;
    }

    public void updateAdmin(Systemadministrator systemadministrator, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        systemadministrator.setUsername(username);
        systemadministrator.setPassword(password);
        systemadministrator.setEmail(email);
        systemadministrator.setFirstName(firstName);
        systemadministrator.setLastName(lastName);
        systemadministrator.setAccessLevel(accessLevel);

        App.getDatabaseManager().updateAdmin(username, password, email, firstName, lastName, accessLevel, systemadministrator.getAdminId());
    }

    public void updateProducer(Producer producer, String username, String password, String email, String firstName, String lastName, int accessLevel, String employedBy) {
        producer.setUsername(username);
        producer.setPassword(password);
        producer.setEmail(email);
        producer.setFirstName(firstName);
        producer.setLastName(lastName);
        producer.setAccessLevel(accessLevel);
        producer.setEmployedBy(employedBy);

        App.getDatabaseManager().updateProducer(username, password, email, firstName, lastName, accessLevel, producer.getProducerId(), employedBy);
    }

    public void deleteAdmin(Systemadministrator systemadministrator) {
        App.getDatabaseManager().deleteAdmin(systemadministrator.getAdminId());
    }

    public void deleteProducer(Producer producer) {
        App.getDatabaseManager().deleteProducer(producer.getProducerId());
    }
}


